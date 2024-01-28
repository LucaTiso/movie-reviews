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
import jakarta.persistence.Table;

@Entity
@Table(name="METACRITIC_USER_REVIEW")
public class MetacriticUserReview {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMDB_REVIEW_SEQ")
	@SequenceGenerator(name = "IMDB_REVIEW_SEQ", sequenceName = "IMDB_REVIEW_SEQ", allocationSize = 1)
	private Long id;
	
	//
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public ImdbUser getImdbUser() {
		return imdbUser;
	}

	public void setImdbUser(ImdbUser imdbUser) {
		this.imdbUser = imdbUser;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}


}
