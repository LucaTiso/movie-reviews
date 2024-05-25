package com.luca.moviereviews.core.model;

import java.time.LocalDate;

public class ReviewSearchParams {

	private Integer pageNumber;

	private Integer pageRecords;

	private String sortBy;

	private String sortDirection;
	
	private String username;
	
	private String text;
	
	private Integer minUserRating;
	
	private Integer maxUserRating;
	
	private LocalDate fromReviewDate;
	
	private LocalDate toReviewDate;
	
	private String title;
	
	public ReviewSearchParams(Integer pageNumber, Integer pageRecords,String sortBy,String sortDirection) {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getMinUserRating() {
		return minUserRating;
	}

	public void setMinUserRating(Integer minUserRating) {
		this.minUserRating = minUserRating;
	}

	public Integer getMaxUserRating() {
		return maxUserRating;
	}

	public void setMaxUserRating(Integer maxUserRating) {
		this.maxUserRating = maxUserRating;
	}

	public LocalDate getFromReviewDate() {
		return fromReviewDate;
	}

	public void setFromReviewDate(LocalDate fromReviewDate) {
		this.fromReviewDate = fromReviewDate;
	}

	public LocalDate getToReviewDate() {
		return toReviewDate;
	}

	public void setToReviewDate(LocalDate toReviewDate) {
		this.toReviewDate = toReviewDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
