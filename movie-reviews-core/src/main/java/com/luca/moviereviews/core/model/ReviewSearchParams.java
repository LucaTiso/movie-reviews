package com.luca.moviereviews.core.model;

public class ReviewSearchParams {

	private Integer pageNumber;

	private Integer pageRecords;

	private String sortBy;

	private String sortDirection;
	
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

}
