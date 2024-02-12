package com.luca.moviereviews.webapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

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
@CrossOrigin(origins="*")
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
			@RequestParam("direction") String direction){
		
		MovieSearchParams movieSearchParams=new MovieSearchParams(pageNumber,itemsPerPage,sortBy,direction);
		MovieSearchResponse movieSearch= movieService.getMovies(movieSearchParams);
		ResponseEntity<MovieSearchResponse> response=new ResponseEntity<>(movieSearch,HttpStatus.OK);
		return response;
		
	}
	
	

}
