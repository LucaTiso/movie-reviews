package com.luca.moviereviews.jpa.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class ImdbUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMDB_USER_SEQ")
	@SequenceGenerator(name = "IMDB_USER_SEQ", sequenceName = "IMDB_USER_SEQ", allocationSize = 1)
	private Long id;

	private String userCode;

	private String username;

	@OneToMany(targetEntity = MetacriticUserReview.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "imdbUser")
	private List<MetacriticUserReview> imdbReviews = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<MetacriticUserReview> getImdbReviews() {
		return imdbReviews;
	}

	public void setImdbReviews(List<MetacriticUserReview> imdbReviews) {
		this.imdbReviews = imdbReviews;
	}

}
