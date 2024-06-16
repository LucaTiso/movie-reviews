package com.luca.moviereviews.webapp.controllers;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.service.MetacriticReviewService;
import com.luca.moviereviews.responses.MetacriticMovieReviewResponse;
import com.luca.moviereviews.responses.MetacriticReviewResponse;
import com.luca.moviereviews.responses.ReviewSearchResponse;

@RestController
@RequestMapping(path="/api/movies")
//@CrossOrigin(origins="*")
public class MetacriticReviewController {

	private final MetacriticReviewService metacriticReviewService;
	
	public MetacriticReviewController(MetacriticReviewService metacriticReviewService) {
		this.metacriticReviewService=metacriticReviewService;
	}
	
	@GetMapping(path="/metacritic/reviews/{reviewId}")
	public ResponseEntity<MetacriticReviewResponse> getReview(@PathVariable Long reviewId) {
		
		MetacriticReviewResponse reviewResponse=metacriticReviewService.getReview(0l, reviewId);
		ResponseEntity<MetacriticReviewResponse> response=new ResponseEntity<>(reviewResponse,HttpStatus.OK);
		return response;
		
	}
	
	@GetMapping(path="/{movieId}/metacritic/reviews")
	public ResponseEntity<ReviewSearchResponse<MetacriticReviewResponse>> getReviews(@PathVariable Long movieId,@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("itemsPerPage") Integer itemsPerPage,
			@RequestParam("sortBy") String sortBy,
			@RequestParam("direction") String direction,
			@RequestParam(required=false,name="username")String username,
			@RequestParam(required=false,name="text")String text,
			@RequestParam(required=false,name="minUserRating")Integer minUserRating,
			@RequestParam(required=false,name="maxUserRating")Integer maxUserRating,
			@RequestParam(required=false,name="fromReviewDate") @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate fromReviewDate,
			@RequestParam(required=false,name="toReviewDate") @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate toReviewDate
			) {
	
				
		ReviewSearchParams reviewSearchParams=new ReviewSearchParams(pageNumber,itemsPerPage,sortBy,direction);
		reviewSearchParams.setUsername(username);
		reviewSearchParams.setText(text);
		reviewSearchParams.setMinUserRating(minUserRating);
		reviewSearchParams.setMaxUserRating(maxUserRating);
		reviewSearchParams.setFromReviewDate(fromReviewDate);
		reviewSearchParams.setToReviewDate(toReviewDate);
		
		ReviewSearchResponse<MetacriticReviewResponse> reviewSearch= metacriticReviewService.getReviews(movieId, reviewSearchParams);
		ResponseEntity<ReviewSearchResponse<MetacriticReviewResponse>> response=new ResponseEntity<>(reviewSearch,HttpStatus.OK);
		return response;
		
	}
	
	@GetMapping(path="/metacritic/reviews")
	public ResponseEntity<ReviewSearchResponse<MetacriticMovieReviewResponse>> getAllMovieReviews(@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("itemsPerPage") Integer itemsPerPage,
			@RequestParam("sortBy") String sortBy,
			@RequestParam("direction") String direction,
			@RequestParam(required=false,name="username")String username,
			@RequestParam(required=false,name="text")String text,
			@RequestParam(required=false,name="minUserRating")Integer minUserRating,
			@RequestParam(required=false,name="maxUserRating")Integer maxUserRating,
			@RequestParam(required=false,name="fromReviewDate") @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate fromReviewDate,
			@RequestParam(required=false,name="toReviewDate") @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate toReviewDate,
			@RequestParam(required=false,name="title")String title) {
		
	
		
		ReviewSearchParams reviewSearchParams=new ReviewSearchParams(pageNumber,itemsPerPage,sortBy,direction);
		reviewSearchParams.setUsername(username);
		reviewSearchParams.setText(text);
		reviewSearchParams.setMinUserRating(minUserRating);
		reviewSearchParams.setMaxUserRating(maxUserRating);
		reviewSearchParams.setFromReviewDate(fromReviewDate);
		reviewSearchParams.setToReviewDate(toReviewDate);
		reviewSearchParams.setTitle(title);
		
		ReviewSearchResponse<MetacriticMovieReviewResponse> reviewSearch= metacriticReviewService.getAllMovieReviews(reviewSearchParams);
		ResponseEntity<ReviewSearchResponse<MetacriticMovieReviewResponse>> response=new ResponseEntity<>(reviewSearch,HttpStatus.OK);
		return response;
		
	}

}
