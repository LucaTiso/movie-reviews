package com.luca.moviereviews.core.service;

import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.requests.WebappReviewRequest;
import com.luca.moviereviews.responses.ReviewSearchResponse;

public interface WebappReviewService {

	public void addReview(Long movieId, WebappReviewRequest request, String username);

	public void updateReview(Long movieId, Long reviewId, WebappReviewRequest request);

	public void deleteReview(Long movieId, Long reviewId);

	public ReviewSearchResponse getReviews(Long movieId, ReviewSearchParams reviewSearchParams);

}
