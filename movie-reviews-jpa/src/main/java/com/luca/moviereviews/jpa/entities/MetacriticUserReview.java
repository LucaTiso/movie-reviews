package com.luca.moviereviews.jpa.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="METACRITIC_USER_REVIEW")
public class MetacriticUserReview {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "METACRITIC_USER_REVIEW_SEQ")
	@SequenceGenerator(name = "METACRITIC_USER_REVIEW_SEQ", sequenceName = "METACRITIC_USER_REVIEW_SEQ", allocationSize = 1)
	private Long id;
	
	private String username;
	
	@Column(name="movie_id")
	private Long movieId;

	private String text;

	private int rating;

	private LocalDate reviewDate;
	
	@ManyToOne(targetEntity = Movie.class, fetch = FetchType.LAZY)
	@JoinColumn(name="movie_id",referencedColumnName="id",insertable=false,updatable=false,nullable=false)
	private Movie movie;


	public Long getId() {
		return id;
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

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Long getMovieId() {
		return movieId;
	}


	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	

}
