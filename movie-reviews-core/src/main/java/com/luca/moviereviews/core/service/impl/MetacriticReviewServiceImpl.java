package com.luca.moviereviews.core.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.requests.MetacriticReviewRequest;
import com.luca.moviereviews.core.service.ImdbReviewService;
import com.luca.moviereviews.jpa.repository.MetacriticReviewRepository;
import com.luca.moviereviews.responses.ReviewSearchResponse;

@Service
public class MetacriticReviewServiceImpl implements ImdbReviewService {

	private final MetacriticReviewRepository imdbReviewRepository;

	public MetacriticReviewServiceImpl(MetacriticReviewRepository imdbReviewRepository) {
		this.imdbReviewRepository = imdbReviewRepository;
	}

	@Override
	@Transactional
	public void updateReview(Long reviewId, MetacriticReviewRequest request) {

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
			
			if(request.getUsername()!=null) {
				r.setUsername(r.getUsername());
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
