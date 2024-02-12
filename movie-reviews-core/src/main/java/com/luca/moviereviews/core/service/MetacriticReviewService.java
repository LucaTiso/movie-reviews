package com.luca.moviereviews.core.service;

import com.luca.moviereviews.responses.MetacriticReviewResponse;

public interface MetacriticReviewService {
	
	
	public MetacriticReviewResponse getReview(Long movieId, Long reviewId);

}
