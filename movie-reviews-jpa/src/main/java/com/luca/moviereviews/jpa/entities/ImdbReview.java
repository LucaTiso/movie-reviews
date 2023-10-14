package com.luca.moviereviews.jpa.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class ImdbReview {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMDB_REVIEW_SEQ")
	@SequenceGenerator(name = "IMDB_REVIEW_SEQ", sequenceName = "IMDB_REVIEW_SEQ", allocationSize = 5)
	private Long id;

	private String title;

	private String text;

	private Integer rating;

	private LocalDate reviewDate;

	private LocalDateTime reviewTime;

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

	@ManyToOne(targetEntity = ImdbUser.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "imdb_user_id", referencedColumnName = "id", nullable = false)
	private ImdbUser imdbUser;

	@ManyToOne(targetEntity = Movie.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
	private Movie movie;

}
