package com.luca.moviereviews.jpa.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="MOVIE_NEW")
public class Movie implements Serializable {

	private static final long serialVersionUID = -699356038420699653L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIE_NEW_SEQ")
	@SequenceGenerator(name = "MOVIE_NEW_SEQ", sequenceName = "MOVIE_NEW_SEQ", allocationSize = 1)
	private Long id;

	private String href;

	private String title;

	private Integer duration;

	private String regia;

	private String production;

	private String star;

	private String movieCast;

	private Integer year;
	
	private String genre;

	private String plot;

	private Float userRating;

	private int userNumRatings;

	private Integer metascore;
	
	private int metascoreNumRatings;
	
	private String movieRatingCategory;
	
	@Column(name="INSERTION_TIMESTAMP")
	private LocalDateTime insertionTimestamp;
	


	@OneToMany(targetEntity = WebappReview.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movie")
	private List<WebappReview> webappReviews = new ArrayList<>();

	@OneToMany(targetEntity = MetacriticUserReview.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movie")
	private List<MetacriticUserReview> metacriticReviews = new ArrayList<>();

	
	public Long getId() {
		return id;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getRegia() {
		return regia;
	}

	public void setRegia(String regia) {
		this.regia = regia;
	}

	
	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}


	public Integer getMetascore() {
		return metascore;
	}

	public void setMetascore(Integer metascore) {
		this.metascore = metascore;
	}

	
	public List<WebappReview> getWebappReviews() {
		return webappReviews;
	}

	public void setWebappReviews(List<WebappReview> webappReviews) {
		this.webappReviews = webappReviews;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Float getUserRating() {
		return userRating;
	}

	public void setUserRating(Float userRating) {
		this.userRating = userRating;
	}

	public int getUserNumRatings() {
		return userNumRatings;
	}

	public void setUserNumRatings(int userNumRatings) {
		this.userNumRatings = userNumRatings;
	}

	public int getMetascoreNumRatings() {
		return metascoreNumRatings;
	}

	public void setMetascoreNumRatings(int metascoreNumRatings) {
		this.metascoreNumRatings = metascoreNumRatings;
	}

	public String getMovieRatingCategory() {
		return movieRatingCategory;
	}

	public void setMovieRatingCategory(String movieRatingCategory) {
		this.movieRatingCategory = movieRatingCategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<MetacriticUserReview> getMetacriticReviews() {
		return metacriticReviews;
	}

	public void setMetacriticReviews(List<MetacriticUserReview> metacriticReviews) {
		this.metacriticReviews = metacriticReviews;
	}

	public String getMovieCast() {
		return movieCast;
	}

	public void setMovieCast(String movieCast) {
		this.movieCast = movieCast;
	}

	public LocalDateTime getInsertionTimestamp() {
		return insertionTimestamp;
	}

	
}
