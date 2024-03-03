package com.luca.moviereviews.webapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.service.MetacriticReviewService;

import com.luca.moviereviews.responses.MetacriticReviewResponse;
import com.luca.moviereviews.responses.ReviewSearchResponse;

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
	
	@GetMapping(path="/{movieId}/metacritic/reviews")
	public ResponseEntity<ReviewSearchResponse<MetacriticReviewResponse>> getReviews(@PathVariable Long movieId,@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("itemsPerPage") Integer itemsPerPage,
			@RequestParam("sortBy") String sortBy,
			@RequestParam("direction") String direction) {
		
		System.out.println("raggiunto controller");
		
		ReviewSearchParams reviewSearchParams=new ReviewSearchParams(pageNumber,itemsPerPage,sortBy,direction);
		ReviewSearchResponse<MetacriticReviewResponse> reviewSearch= metacriticReviewService.getReviews(movieId, reviewSearchParams);
		ResponseEntity<ReviewSearchResponse<MetacriticReviewResponse>> response=new ResponseEntity<>(reviewSearch,HttpStatus.OK);
		return response;
		
	}

}
