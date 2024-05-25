package com.luca.moviereviews.responses.dto;

public class MovieSearchDto {
	
	private Long id;

	private String href;

	private String title;

	private Integer duration;

	private Integer year;
	
	private Float userRating;

	private int userNumRatings;

	private Integer metascore;
	
	private int metascoreNumRatings;
	
	private boolean favourite;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Float getUserRating() {
		return userRating;
	}

	public void setUserRating(Float userRating) {
		this.userRating = userRating;
	}

	public int getUserNumRatings() {
		return userNumRatings;
	}

	public void setUserNumRatings(int userNumRatings) {
		this.userNumRatings = userNumRatings;
	}

	public Integer getMetascore() {
		return metascore;
	}

	public void setMetascore(Integer metascore) {
		this.metascore = metascore;
	}

	public int getMetascoreNumRatings() {
		return metascoreNumRatings;
	}

	public void setMetascoreNumRatings(int metascoreNumRatings) {
		this.metascoreNumRatings = metascoreNumRatings;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}
	
}
