package com.luca.moviereviews.responses;

public class FavouriteMappingResponse {
	
	private int position;

	private MovieResponse movieResponse;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public MovieResponse getMovieResponse() {
		return movieResponse;
	}

	public void setMovieResponse(MovieResponse movieResponse) {
		this.movieResponse = movieResponse;
	}
	
	


}
