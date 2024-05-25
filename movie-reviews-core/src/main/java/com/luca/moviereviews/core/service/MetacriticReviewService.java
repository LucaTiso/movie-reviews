package com.luca.moviereviews.core.service;

import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.responses.MetacriticMovieReviewResponse;
import com.luca.moviereviews.responses.MetacriticReviewResponse;
import com.luca.moviereviews.responses.ReviewSearchResponse;

public interface MetacriticReviewService {
	
	
	public MetacriticReviewResponse getReview(Long movieId, Long reviewId);
	
	public ReviewSearchResponse<MetacriticReviewResponse> getReviews(Long movieId, ReviewSearchParams reviewSearchParams);
	
	public ReviewSearchResponse<MetacriticMovieReviewResponse> getAllMovieReviews(ReviewSearchParams reviewSearchParams);

}