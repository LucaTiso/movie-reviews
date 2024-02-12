package com.luca.moviereviews.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.requests.WebappReviewRequest;
import com.luca.moviereviews.core.service.WebappReviewService;
import com.luca.moviereviews.core.utils.EntityUtils;
import com.luca.moviereviews.jpa.entities.WebappReview;
import com.luca.moviereviews.jpa.entities.WebappUser;
import com.luca.moviereviews.jpa.repository.WebappReviewRepository;
import com.luca.moviereviews.jpa.repository.WebappUserRepository;
import com.luca.moviereviews.responses.WebappReviewResponse;
import com.luca.moviereviews.responses.ReviewSearchResponse;

@Service
public class WebappReviewServiceImpl implements WebappReviewService {

	
	private final WebappReviewRepository webappReviewRepository;

	
	private final WebappUserRepository webappUserRepository;

	
	public WebappReviewServiceImpl(WebappReviewRepository webappReviewRepository,WebappUserRepository webappUserRepository) {
		this.webappReviewRepository=webappReviewRepository;
		this.webappUserRepository=webappUserRepository;
	}

	@Override
	@Transactional
	public void addReview(Long movieId, WebappReviewRequest request) {

		WebappUser user=webappUserRepository.findByUsername(request.getUsername()).orElseThrow(()-> new RuntimeException("Utente non trovato"));
			WebappReview webappReview = EntityUtils.dtoToEntity(request);
			webappReview.setMovieId(movieId);
			webappReview.setWebappUser(user);
			webappReviewRepository.save(webappReview);
			
	}

	@Override
	@Transactional
	public void updateReview(Long movieId, Long reviewId, WebappReviewRequest request) {
		Optional<WebappReview> optionalReview = webappReviewRepository.findById(reviewId);

		if (optionalReview.isPresent()) {
			WebappReview review = optionalReview.get();
			review.setRating(request.getRating());
			review.setText(request.getText());
			//review.setUsername(request.getUsername());
		}
	}

	@Override
	@Transactional
	public void deleteReview(Long movieId, Long reviewId) {

		webappReviewRepository.deleteById(reviewId);

	}

	@Override
	@Transactional(readOnly = true)
	public ReviewSearchResponse getReviews(Long movieId, ReviewSearchParams reviewSearchParams) {
		
	
		Sort sort = reviewSearchParams.getSortDirection().toUpperCase().contentEquals("DESC")
				? Sort.by(reviewSearchParams.getSortBy()).descending()
				: Sort.by(reviewSearchParams.getSortBy()).ascending();

		Specification<WebappReview> spec = (root, query, cb) -> {
			return cb.equal(root.get("movieId"), movieId);
		};

		Page<WebappReview> reviewPage = webappReviewRepository.findAll(spec,
				PageRequest.of(reviewSearchParams.getPageNumber(), reviewSearchParams.getPageRecords(), sort));

		List<WebappReviewResponse> reviewList = reviewPage.getContent().stream().map(EntityUtils::entityToDto).toList();

		ReviewSearchResponse searchResponse = new ReviewSearchResponse();
		searchResponse.setReviewList(reviewList);
		searchResponse.setPageNumber(reviewPage.getNumber());
		searchResponse.setTotalNumber(reviewPage.getTotalElements());

		// if first page this should be 0
		Long numPreviousPage = reviewSearchParams.getPageNumber() * reviewSearchParams.getPageRecords() * 1l;

		searchResponse.setFromNum(numPreviousPage + 1);
		searchResponse.setToNum(numPreviousPage + reviewPage.getNumberOfElements());

		return searchResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public WebappReviewResponse getReview(Long movieId, Long reviewId) {
		
		return webappReviewRepository.findById(reviewId).map(EntityUtils::entityToDto).orElseThrow(()->new RuntimeException("Ops"));
	
		
	}

}
