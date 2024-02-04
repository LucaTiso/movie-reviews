package com.luca.moviereviews.core.requests;

public class MovieRequest {

	private String title;
	
	private String href;

	private Integer duration;

	private String regia;

	

	private String star;

	private String cast;

	private Integer year;

	private String plot;

	private Float userRating;
	
	private Integer userNumRatings;


	private Integer metascore;
	
	private Integer metascoreNumRatings;
	
	private String genre;
	
	private String movieRatingCategory;
	
	private String production;
	
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


	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	
	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}



	public Integer getMetascore() {
		return metascore;
	}

	public void setMetascore(Integer metascore) {
		this.metascore = metascore;
	}


	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getMetascoreNumRatings() {
		return metascoreNumRatings;
	}

	public void setMetascoreNumRatings(Integer metascoreNumRatings) {
		this.metascoreNumRatings = metascoreNumRatings;
	}

	public String getMovieRatingCategory() {
		return movieRatingCategory;
	}

	public void setMovieRatingCategory(String movieRatingCategory) {
		this.movieRatingCategory = movieRatingCategory;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getUserNumRatings() {
		return userNumRatings;
	}

	public void setUserNumRatings(Integer userNumRatings) {
		this.userNumRatings = userNumRatings;
	}

	public Float getUserRating() {
		return userRating;
	}

	public void setUserRating(Float userRating) {
		this.userRating = userRating;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
}
