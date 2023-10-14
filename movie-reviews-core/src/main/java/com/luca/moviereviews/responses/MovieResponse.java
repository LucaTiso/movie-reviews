package com.luca.moviereviews.responses;

public class MovieResponse {
	
	private Long id;
	
	private String movieCode;
	
	private String title;
	
	private String originalTitle;
	
	private Integer year;
	
	private Integer duration;
	
	private String regia;
	
	private String sceneggiatura;
	
	private String star;
	
	private String movieCast;
	
	private String plot;
	
	private Float rating;
	
	private Integer metascore;
	
	private Integer imdbRating;
	
	private Long numRatings;
	
	private Integer currentPosition;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getMetascore() {
		return metascore;
	}

	public void setMetascore(Integer metascore) {
		this.metascore = metascore;
	}

	public Integer getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Integer imdbRating) {
		this.imdbRating = imdbRating;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
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

	public Integer getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Integer currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
