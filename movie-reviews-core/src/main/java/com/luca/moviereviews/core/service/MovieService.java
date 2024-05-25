package com.luca.moviereviews.core.service;

import com.luca.moviereviews.core.model.MovieSearchParams;
import com.luca.moviereviews.core.requests.MovieRequest;
import com.luca.moviereviews.responses.MovieResponse;
import com.luca.moviereviews.responses.MovieSearchResponse;

public interface MovieService {
	
	void saveMovie(MovieRequest movieRequest);
	
	void updateMovie(MovieRequest movieRequest,Long id);
	
	MovieSearchResponse getMovies(MovieSearchParams movieSearchParams);
	
	
	MovieSearchResponse getRecentMovies();
	
	MovieResponse getMovie(Long id);

}
