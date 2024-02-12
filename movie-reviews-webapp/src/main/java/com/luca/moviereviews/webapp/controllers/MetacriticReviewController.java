package com.luca.moviereviews.webapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.luca.moviereviews.core.service.MetacriticReviewService;

import com.luca.moviereviews.responses.MetacriticReviewResponse;

@RestController
@RequestMapping(path="/api/movies")
public class MetacriticReviewController {

	private final MetacriticReviewService metacriticReviewService;
	
	public MetacriticReviewController(MetacriticReviewService metacriticReviewService) {
		this.metacriticReviewService=metacriticReviewService;
	}
	
	@GetMapping(path="/{movieId}/metacritic/reviews/{reviewId}")
	public ResponseEntity<MetacriticReviewResponse> getReview(@PathVariable Long movieId,@PathVariable Long reviewId) {
		
		MetacriticReviewResponse reviewResponse=metacriticReviewService.getReview(movieId, reviewId);
		ResponseEntity<MetacriticReviewResponse> response=new ResponseEntity<>(reviewResponse,HttpStatus.OK);
		return response;
		
	}

}
