package com.luca.moviereviews.responses;

import java.util.List;

public class ReviewSearchResponse {

	private List<WebappReviewResponse> reviewList;

	private int pageNumber;

	private Long totalNumber;

	private Long fromNum;

	private Long toNum;

	public List<WebappReviewResponse> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<WebappReviewResponse> reviewList) {
		this.reviewList = reviewList;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Long getFromNum() {
		return fromNum;
	}

	public void setFromNum(Long fromNum) {
		this.fromNum = fromNum;
	}

	public Long getToNum() {
		return toNum;
	}

	public void setToNum(Long toNum) {
		this.toNum = toNum;
	}
	
}
