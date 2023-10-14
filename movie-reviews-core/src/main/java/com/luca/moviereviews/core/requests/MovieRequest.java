package com.luca.moviereviews.core.requests;

public class MovieRequest {

	private String movieName;
	
	private String movieCode;

	private String originalName;

	private Integer duration;

	private String regia;

	private String sceneggiatura;

	private String star;

	private String movieCast;

	private Integer movieYear;

	private String plot;

	private Float rating;

	private Long numRatings;

	private Long numFavourites;

	private Integer metascore;
	
	private Integer currentPosition;
	
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getRegia() {
		return regia;
	}

	public void setRegia(String regia) {
		this.regia = regia;
	}

	public String getSceneggiatura() {
		return sceneggiatura;
	}

	public void setSceneggiatura(String sceneggiatura) {
		this.sceneggiatura = sceneggiatura;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getMovieCast() {
		return movieCast;
	}

	public void setMovieCast(String movieCast) {
		this.movieCast = movieCast;
	}

	public Integer getMovieYear() {
		return movieYear;
	}

	public void setMovieYear(Integer movieYear) {
		this.movieYear = movieYear;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Long getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(Long numRatings) {
		this.numRatings = numRatings;
	}

	public Long getNumFavourites() {
		return numFavourites;
	}

	public void setNumFavourites(Long numFavourites) {
		this.numFavourites = numFavourites;
	}

	public Integer getMetascore() {
		return metascore;
	}

	public void setMetascore(Integer metascore) {
		this.metascore = metascore;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public Integer getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Integer currentPosition) {
		this.currentPosition = currentPosition;
	}
	
}
