package com.luca.moviereviews.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.requests.WebappReviewRequest;
import com.luca.moviereviews.core.service.WebappReviewService;
import com.luca.moviereviews.responses.ReviewSearchResponse;

@RestController
@RequestMapping(path="/api/movies")
public class ReviewController {
	
	
	@Autowired
	private WebappReviewService webappReviewService;
	
	
	@PostMapping(path="/{id}/reviews")
	public ResponseEntity<?> addReview(@PathVariable Long id,@RequestHeader("username") String username,@RequestBody WebappReviewRequest webappReviewRequest) {
		
		webappReviewService.addReview(id, webappReviewRequest, username);
		ResponseEntity<?> response=new ResponseEntity<>(HttpStatus.OK);
		
		return response;
	}
	
	@DeleteMapping(path="/{movieId}/reviews/{reviewId}")
	public void deeteReview(@PathVariable Long movieId,@PathVariable Long reviewId) {
		
	}
	
	@PutMapping(path="/{movieId}/reviews/{reviewId}")
	public void updateReview(@PathVariable Long movieId,@PathVariable Long reviewId,@RequestBody WebappReviewRequest webappReviewRequest) {
		
	}
	
	@GetMapping(path="/{movieId}/reviews")
	public ResponseEntity<ReviewSearchResponse> getReviews(@PathVariable Long movieId,@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("itemsPerPage") Integer itemsPerPage,
			@RequestParam("sortBy") String sortBy,
			@RequestParam("direction") String direction) {
		
		ReviewSearchParams reviewSearchParams=new ReviewSearchParams(pageNumber,itemsPerPage,sortBy,direction);
		ReviewSearchResponse reviewSearch= webappReviewService.getReviews(movieId, reviewSearchParams);
		ResponseEntity<ReviewSearchResponse> response=new ResponseEntity<>(reviewSearch,HttpStatus.OK);
		return response;
		
	}

}
