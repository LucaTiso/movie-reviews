package com.luca.moviereviews.responses;

public class WebappMovieReviewResponse extends WebappReviewResponse{
	
	
	private Long movieId;
	
	private String movieTitle;

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	
	

}
