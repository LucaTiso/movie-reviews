package com.luca.moviereviews.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.requests.WebappReviewRequest;
import com.luca.moviereviews.core.service.WebappReviewService;
import com.luca.moviereviews.core.utils.EntityUtils;
import com.luca.moviereviews.jpa.entities.SecurityUser;
import com.luca.moviereviews.jpa.entities.WebappReview;
import com.luca.moviereviews.jpa.repository.SecurityUserRepository;
import com.luca.moviereviews.jpa.repository.WebappReviewRepository;
import com.luca.moviereviews.responses.WebappReviewResponse;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

import com.luca.moviereviews.responses.ReviewSearchResponse;

@Service
public class WebappReviewServiceImpl implements WebappReviewService {

	private final WebappReviewRepository webappReviewRepository;

	private final SecurityUserRepository securityUserRepository;

	public WebappReviewServiceImpl(WebappReviewRepository webappReviewRepository,
			SecurityUserRepository securityUserRepository) {
		this.webappReviewRepository = webappReviewRepository;
		this.securityUserRepository = securityUserRepository;
	}

	@Override
	@Transactional
	public void addReview(Long movieId, WebappReviewRequest request) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		webappReviewRepository.findByMovieAndUsername(movieId, userDetails.getUsername()).ifPresent(s -> {
			throw new RuntimeException("L'utente ha gia scritto una recensione per questo film");
		});

		SecurityUser user = securityUserRepository.findByUsername(userDetails.getUsername()).orElseThrow(
				() -> new RuntimeException("utente non trovato con username : " + userDetails.getUsername()));

		WebappReview webappReview = EntityUtils.dtoToEntity(request);
		webappReview.setMovieId(movieId);
		webappReview.setWebappUser(user);
		webappReviewRepository.save(webappReview);

	}

	@Override
	@Transactional
	public void updateReview(Long movieId, Long reviewId, WebappReviewRequest request) {

		WebappReview review = webappReviewRepository.findById(reviewId)
				.orElseThrow(() -> new RuntimeException("recensione non trovata per id : " + reviewId));

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (review.getWebappUser().getUsername().contentEquals(userDetails.getUsername())) {

			review.setRating(request.getRating());
			review.setText(request.getText());

		}
	}

	@Override
	@Transactional
	public void deleteReview(Long movieId, Long reviewId) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		WebappReview review = webappReviewRepository.findById(reviewId)
				.orElseThrow(() -> new RuntimeException("recensione non trovata per id : " + reviewId));

		if (review.getWebappUser().getUsername().contentEquals(userDetails.getUsername())) {
			webappReviewRepository.delete(review);
		} else {
			throw new RuntimeException("La recensione cod id: " + reviewId + " appartiene a un utente diverso");
		}

	}

	@Override
	@Transactional(readOnly = true)
	public ReviewSearchResponse<WebappReviewResponse> getReviews(Long movieId, ReviewSearchParams reviewSearchParams) {

		Sort sort = reviewSearchParams.getSortDirection().toUpperCase().contentEquals("DESC")
				? Sort.by(reviewSearchParams.getSortBy()).descending()
				: Sort.by(reviewSearchParams.getSortBy()).ascending();

		Specification<WebappReview> spec = (root, query, cb) -> {
			//return cb.equal(root.get("movieId"), movieId);
			
			List<Predicate> predicates = new ArrayList<>();
			
			String text="bello";
			Integer rating =null;
			String username="user";
			
			 Join<WebappReview, SecurityUser> userJoin = root.join("webappUser");

			if (text != null && !text.isEmpty()) {
				predicates.add(cb.like(cb.lower(root.get("text")), "%" + text.toLowerCase() + "%"));
			}

			if (rating != null) {
				predicates.add(cb.equal(root.get("rating"), rating));
			}
			
			if(username!=null && !username.isEmpty()) {
				predicates.add(cb.like(cb.lower(userJoin.get("username")), "%" + username.toLowerCase() + "%"));
			}
			
			 predicates.add(cb.equal(root.get("movieId"), movieId));

			return cb.and(predicates.toArray(new Predicate[0]));
		};
		
		System.out.println("page number: "+reviewSearchParams.getPageNumber());
		System.out.println("page number: "+reviewSearchParams.getPageRecords());
		System.out.println("page number: "+reviewSearchParams.getPageNumber());
		

		Page<WebappReview> reviewPage = webappReviewRepository.findAll(spec,
				PageRequest.of(reviewSearchParams.getPageNumber()-1, reviewSearchParams.getPageRecords(), sort));
		
		
		System.out.println(reviewPage.getContent().size());

		List<WebappReviewResponse> reviewList = reviewPage.getContent().stream().map(EntityUtils::entityToDto).toList();

		ReviewSearchResponse<WebappReviewResponse> searchResponse = new ReviewSearchResponse<>();
		searchResponse.setReviewList(reviewList);
		searchResponse.setPageNumber(reviewSearchParams.getPageNumber());
		searchResponse.setTotalNumber(reviewPage.getTotalElements());

		// if first page this should be 0
		Long numPreviousPage = (reviewSearchParams.getPageNumber()-1) * reviewSearchParams.getPageRecords() * 1l;

		searchResponse.setFromNum(numPreviousPage + 1);
		searchResponse.setToNum(numPreviousPage + reviewPage.getNumberOfElements());

		return searchResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public WebappReviewResponse getReview(Long movieId, Long reviewId) {

		return webappReviewRepository.findById(reviewId).map(EntityUtils::entityToDto)
				.orElseThrow(() -> new RuntimeException("Ops"));

	}

}
