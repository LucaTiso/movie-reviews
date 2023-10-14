package com.luca.moviereviews.webapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.luca.moviereviews.core.model.MovieSearchParams;
import com.luca.moviereviews.core.requests.MovieRequest;
import com.luca.moviereviews.core.service.MovieService;
import com.luca.moviereviews.responses.MovieSearchResponse;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "/api/movies")
public class MovieController {

	// by default return the first page of movies ordered by popularity
	// visible by alla users

	@Autowired
	private MovieService movieService;

	// update movie info if needed
	// take movie info in request body
	// only for admin

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
