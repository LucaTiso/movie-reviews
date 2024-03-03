package com.luca.moviereviews.jpa.entities;

import java.time.LocalDate;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class WebappReview {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WEBAPP_REVIEW_SEQ")
	@SequenceGenerator(name = "WEBAPP_REVIEW_SEQ", sequenceName = "WEBAPP_REVIEW_SEQ", allocationSize = 1)
	private Long id;
		
	private String text;
	
	private int rating;
	
	private String username;
	
	private LocalDate reviewDate;
	
	private LocalDateTime reviewTime;
	
	@Column(name="movie_id")
	private Long movieId;
	
	@ManyToOne(targetEntity = SecurityUser.class, fetch = FetchType.LAZY)
	@JoinColumn(name="webapp_user_id",referencedColumnName="id",nullable=false)
	private SecurityUser webappUser;
	
	@ManyToOne(targetEntity = Movie.class, fetch = FetchType.LAZY)
	@JoinColumn(name="movie_id",referencedColumnName="id",insertable=false,updatable=false,nullable=false)
	private Movie movie;

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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
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

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public SecurityUser getWebappUser() {
		return webappUser;
	}

	public void setWebappUser(SecurityUser webappUser) {
		this.webappUser = webappUser;
	}
	
	
	
}
