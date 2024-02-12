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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.requests.WebappReviewRequest;
import com.luca.moviereviews.core.service.WebappReviewService;
import com.luca.moviereviews.responses.ReviewSearchResponse;
import com.luca.moviereviews.responses.WebappReviewResponse;

@RestController
@RequestMapping(path="/api/movies")
public class WebappReviewController {
	
	@Autowired
	private WebappReviewService webappReviewService;
	
	
	@PostMapping(path="/{id}/webapp/reviews")
	public ResponseEntity<?> addReview(@PathVariable Long id,@RequestBody WebappReviewRequest webappReviewRequest) {
		
		webappReviewService.addReview(id, webappReviewRequest);
		ResponseEntity<?> response=new ResponseEntity<>(HttpStatus.OK);
		
		return response;
	}
	
	@DeleteMapping(path="/{movieId}/webapp/reviews/{reviewId}")
	public void deleteReview(@PathVariable Long movieId,@PathVariable Long reviewId) {
		webappReviewService.deleteReview(movieId, reviewId);
	}
	
	@PutMapping(path="/{movieId}/webapp/reviews/{reviewId}")
	public void updateReview(@PathVariable Long movieId,@PathVariable Long reviewId,@RequestBody WebappReviewRequest webappReviewRequest) {
		webappReviewService.updateReview(movieId, reviewId, webappReviewRequest);
	}
	
	@GetMapping(path="/{movieId}/webapp/reviews/{reviewId}")
	public ResponseEntity<WebappReviewResponse> getReview(@PathVariable Long movieId,@PathVariable Long reviewId) {
		
		WebappReviewResponse reviewResponse=webappReviewService.getReview(movieId, reviewId);
		
		ResponseEntity<WebappReviewResponse> response=new ResponseEntity<>(reviewResponse,HttpStatus.OK);
		return response;
		
	}
	
	@GetMapping(path="/{movieId}/webapp/reviews")
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
