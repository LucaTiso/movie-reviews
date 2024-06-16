package com.luca.moviereviews.webapp.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.luca.moviereviews.core.model.MovieSearchParams;
import com.luca.moviereviews.core.service.MovieService;
import com.luca.moviereviews.responses.MovieResponse;
import com.luca.moviereviews.responses.MovieSearchResponse;

@RestController
//@CrossOrigin(origins="*")
@RequestMapping(path = "/api/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	/*

	@PatchMapping(path="/{id}")
	
	public ResponseEntity<?> updateMovie(@RequestBody MovieRequest movieRequest,@PathVariable Long id) {

		movieService.updateMovie(movieRequest, id);
		ResponseEntity<?> response=new ResponseEntity<>(HttpStatus.OK);
		return response;
	}

	@PostMapping
	public ResponseEntity<?> saveMovie(@RequestBody MovieRequest movieRequest) {
		
		movieService.saveMovie(movieRequest);
		
		ResponseEntity<?> response=new ResponseEntity<>(HttpStatus.OK);
		return response;
	}
	
	
	@DeleteMapping(path="/{id}")
	public  ResponseEntity<?> deleteMovie(@PathVariable Long id){
		return null;
	}
	*/
	
	
	@GetMapping(path="/recent")
	public ResponseEntity<MovieSearchResponse> getBestRecentMovies() {

		MovieSearchResponse movieResponse=movieService.getRecentMovies();
		
		ResponseEntity<MovieSearchResponse> response=new ResponseEntity<>(movieResponse,HttpStatus.OK);
		
		return response;
	}
	
	
	
	@GetMapping(path="/{id}")
	public ResponseEntity<MovieResponse> getMovie(@PathVariable Long id) {

		MovieResponse movieResponse=movieService.getMovie(id);
		ResponseEntity<MovieResponse> response=new ResponseEntity<>(movieResponse,HttpStatus.OK);
		return response;
	}
	
	@GetMapping
	public ResponseEntity<MovieSearchResponse> searchMovies(@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("itemsPerPage") Integer itemsPerPage,
			@RequestParam("sortBy") String sortBy,
			@RequestParam("direction") String direction,
			@RequestParam(required=false,name="title")String title,
			@RequestParam(required=false,name="minUserRating")Float minUserRating,
			@RequestParam(required=false,name="maxUserRating")Float maxUserRating,
			@RequestParam(required=false,name="minMetascore")Integer minMetascore,
			@RequestParam(required=false,name="maxMetascore")Integer maxMetascore,
			@RequestParam(required=false,name="minUserNumRating")Integer minUserNumRating,
			@RequestParam(required=false,name="maxUserNumRating")Integer maxUserNumRating,
			@RequestParam(required=false,name="fromYear") Integer fromYear,
			@RequestParam(required=false,name="toYear") Integer toYear,
			@RequestParam(required=false,name="genres") List<String> genres){
		
		MovieSearchParams movieSearchParams=new MovieSearchParams(pageNumber,itemsPerPage,sortBy,direction);
		movieSearchParams.setTitle(title);
		movieSearchParams.setMinUserRating(minUserRating);
		movieSearchParams.setMaxUserRating(maxUserRating);
		movieSearchParams.setMinMetascore(minMetascore);
		movieSearchParams.setMaxMetascore(maxMetascore);
		movieSearchParams.setMinUserNumRating(minUserNumRating);
		movieSearchParams.setMaxUserNumRating(maxUserNumRating);
		movieSearchParams.setFromYear(fromYear);
		movieSearchParams.setToYear(toYear);
		movieSearchParams.setGenres(genres);
		
		
		MovieSearchResponse movieSearch= movieService.getMovies(movieSearchParams);
		ResponseEntity<MovieSearchResponse> response=new ResponseEntity<>(movieSearch,HttpStatus.OK);
		return response;
		
	}
	
	

}
