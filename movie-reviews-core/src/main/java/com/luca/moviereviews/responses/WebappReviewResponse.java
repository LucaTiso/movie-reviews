package com.luca.moviereviews.responses;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WebappReviewResponse {

	private Long id;

	private String username;

	private String text;

	private Integer rating;

	private LocalDate reviewDate;

	private LocalDateTime reviewTime;
	
	private String reviewerUsername;
	
	private Long reviewerId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	public LocalDateTime getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(LocalDateTime reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getReviewerUsername() {
		return reviewerUsername;
	}

	public void setReviewerUsername(String reviewerUsername) {
		this.reviewerUsername = reviewerUsername;
	}

	public Long getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(Long reviewerId) {
		this.reviewerId = reviewerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
