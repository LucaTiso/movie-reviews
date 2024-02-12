package com.luca.moviereviews.core.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luca.moviereviews.core.service.MetacriticReviewService;
import com.luca.moviereviews.core.utils.EntityUtils;
import com.luca.moviereviews.jpa.repository.MetacriticReviewRepository;
import com.luca.moviereviews.responses.MetacriticReviewResponse;

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
}
