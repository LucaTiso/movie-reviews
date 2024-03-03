package com.luca.moviereviews.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.service.MetacriticReviewService;
import com.luca.moviereviews.core.utils.EntityUtils;
import com.luca.moviereviews.jpa.entities.MetacriticUserReview;
import com.luca.moviereviews.jpa.repository.MetacriticReviewRepository;
import com.luca.moviereviews.responses.MetacriticReviewResponse;
import com.luca.moviereviews.responses.ReviewSearchResponse;
import jakarta.persistence.criteria.Predicate;

@Service
public class MetacriticReviewServiceImpl implements MetacriticReviewService {

	private final MetacriticReviewRepository metacriticReviewRepository;

	public MetacriticReviewServiceImpl(MetacriticReviewRepository metacriticReviewRepository) {
		this.metacriticReviewRepository = metacriticReviewRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public MetacriticReviewResponse getReview(Long movieId, Long reviewId) {
		return metacriticReviewRepository.findById(reviewId).map(EntityUtils::entityToDto)
				.orElseThrow(() -> new RuntimeException("ops"));

	}

	@Override
	@Transactional(readOnly = true)
	public ReviewSearchResponse<MetacriticReviewResponse> getReviews(Long movieId, ReviewSearchParams reviewSearchParams) {
		Sort sort = reviewSearchParams.getSortDirection().toUpperCase().contentEquals("DESC")
				? Sort.by(reviewSearchParams.getSortBy()).descending()
				: Sort.by(reviewSearchParams.getSortBy()).ascending();
		
		System.out.println("endpoint raggiunto");

		Specification<MetacriticUserReview> spec = (root, query, cb) -> {
			

			List<Predicate> predicates = new ArrayList<>();

			String text = null;
			Integer rating = null;
			String username = null;

			
			if (text != null && !text.isEmpty()) {
				predicates.add(cb.like(cb.lower(root.get("text")), "%" + text.toLowerCase() + "%"));
			}

			if (rating != null) {
				predicates.add(cb.equal(root.get("rating"), rating));
			}

			if (username != null && !username.isEmpty()) {
				predicates.add(cb.like(cb.lower(root.get("username")), "%" + username.toLowerCase() + "%"));
			}
			
			 predicates.add(cb.equal(root.get("movieId"), movieId));

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		Page<MetacriticUserReview> reviewPage = metacriticReviewRepository.findAll(spec,
				PageRequest.of(reviewSearchParams.getPageNumber()-1, reviewSearchParams.getPageRecords(), sort));

		List<MetacriticReviewResponse> reviewList = reviewPage.getContent().stream().map(EntityUtils::entityToDto).toList();

		ReviewSearchResponse<MetacriticReviewResponse> searchResponse = new ReviewSearchResponse<>();
		searchResponse.setReviewList(reviewList);
		searchResponse.setPageNumber(reviewSearchParams.getPageNumber());
		searchResponse.setTotalNumber(reviewPage.getTotalElements());

		// if first page this should be 0
		Long numPreviousPage = (reviewSearchParams.getPageNumber()-1) * reviewSearchParams.getPageRecords() * 1l;

		searchResponse.setFromNum(numPreviousPage + 1);
		searchResponse.setToNum(numPreviousPage + reviewPage.getNumberOfElements());

		return searchResponse;
	}
}
