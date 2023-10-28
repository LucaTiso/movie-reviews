package com.luca.moviereviews.core.service;

import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.requests.ImdbReviewRequest;
import com.luca.moviereviews.responses.ReviewSearchResponse;

public interface ImdbReviewService {
	
	public void updateReview(Long reviewId, ImdbReviewRequest request);

	public void deleteReview(Long reviewId);

	public ReviewSearchResponse getReviews(Long movieId, ReviewSearchParams reviewSearchParams);
	

}
