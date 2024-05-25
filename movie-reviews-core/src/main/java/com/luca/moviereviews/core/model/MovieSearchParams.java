package com.luca.moviereviews.core.model;

import java.util.List;

public class MovieSearchParams {
	
	private Integer pageNumber;
	
	private Integer pageRecords;
	
	private String sortBy;
	
	private String sortDirection;
	
	private String title;
	
	private Float minUserRating;
	
	private Float maxUserRating;
	
	private Integer minMetascore;
	
	private Integer maxMetascore;
	
	private Integer minUserNumRating;
	
	private Integer maxUserNumRating;
	
	private Integer fromYear;
	
	private Integer toYear;
	
	private List<String> genres;
	

	public MovieSearchParams(Integer pageNumber, Integer pageRecords,String sortBy,String sortDirection) {
		this.pageNumber = pageNumber;
		this.pageRecords = pageRecords;
		this.sortBy=sortBy;
		this.sortDirection=sortDirection;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageRecords() {
		return pageRecords;
	}

	public void setPageRecords(Integer pageRecords) {
		this.pageRecords = pageRecords;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getMinUserRating() {
		return minUserRating;
	}

	public void setMinUserRating(Float minUserRating) {
		this.minUserRating = minUserRating;
	}

	public Float getMaxUserRating() {
		return maxUserRating;
	}

	public void setMaxUserRating(Float maxUserRating) {
		this.maxUserRating = maxUserRating;
	}

	public Integer getMinMetascore() {
		return minMetascore;
	}

	public void setMinMetascore(Integer minMetascore) {
		this.minMetascore = minMetascore;
	}

	public Integer getMaxMetascore() {
		return maxMetascore;
	}

	public void setMaxMetascore(Integer maxMetascore) {
		this.maxMetascore = maxMetascore;
	}

	public Integer getMinUserNumRating() {
		return minUserNumRating;
	}

	public void setMinUserNumRating(Integer minUserNumRating) {
		this.minUserNumRating = minUserNumRating;
	}

	public Integer getMaxUserNumRating() {
		return maxUserNumRating;
	}

	public void setMaxUserNumRating(Integer maxUserNumRating) {
		this.maxUserNumRating = maxUserNumRating;
	}

	public Integer getFromYear() {
		return fromYear;
	}

	public void setFromYear(Integer fromYear) {
		this.fromYear = fromYear;
	}

	public Integer getToYear() {
		return toYear;
	}

	public void setToYear(Integer toYear) {
		this.toYear = toYear;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	

}
