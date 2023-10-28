package com.luca.moviereviews.core.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.requests.ImdbReviewRequest;

import com.luca.moviereviews.core.service.ImdbReviewService;
import com.luca.moviereviews.jpa.repository.ImdbReviewRepository;
import com.luca.moviereviews.responses.ReviewSearchResponse;

@Service
public class ImdbReviewServiceImpl implements ImdbReviewService {

	private final ImdbReviewRepository imdbReviewRepository;

	public ImdbReviewServiceImpl(ImdbReviewRepository imdbReviewRepository) {
		this.imdbReviewRepository = imdbReviewRepository;
	}

	@Override
	@Transactional
	public void updateReview(Long reviewId, ImdbReviewRequest request) {

		imdbReviewRepository.findById(reviewId).ifPresentOrElse(r -> {
			if(request.getText()!=null) {
				r.setText(request.getText());
			}
			if(request.getRating()!=null) {
				r.setRating(request.getRating());
			}
			if(request.getReviewDate()!=null) {
				r.setReviewDate(request.getReviewDate());
			}
			if(request.getTitle()!=null) {
				r.setTitle(request.getTitle());
			}
			
		}, () -> {
			throw new RuntimeException("Errore");
		});

	}

	@Override
	@Transactional
	public void deleteReview(Long reviewId) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public ReviewSearchResponse getReviews(Long movieId, ReviewSearchParams reviewSearchParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
