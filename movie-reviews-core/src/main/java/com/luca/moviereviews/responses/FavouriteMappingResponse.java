package com.luca.moviereviews.responses;

public class FavouriteMappingResponse {
	
	private int position;

	private MovieResponse movieResponse;
	
	private Long id;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
