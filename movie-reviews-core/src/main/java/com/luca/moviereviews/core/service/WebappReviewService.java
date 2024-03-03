package com.luca.moviereviews.core.service;

import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.requests.WebappReviewRequest;
import com.luca.moviereviews.responses.ReviewSearchResponse;
import com.luca.moviereviews.responses.WebappReviewResponse;

public interface WebappReviewService {

	public void addReview(Long movieId, WebappReviewRequest request);

	public void updateReview(Long movieId, Long reviewId, WebappReviewRequest request);

	public void deleteReview(Long movieId, Long reviewId);
	
	public WebappReviewResponse getReview(Long movieId, Long reviewId);

	public ReviewSearchResponse<WebappReviewResponse> getReviews(Long movieId, ReviewSearchParams reviewSearchParams);

}
