package com.luca.moviereviews.jpa.entities;

import java.io.Serializable;
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
public class Movie implements Serializable {
	

	private static final long serialVersionUID = -699356038420699653L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
		    generator = "MOVIE_SEQ")
	@SequenceGenerator(name = "MOVIE_SEQ",sequenceName="MOVIE_SEQ",
		    allocationSize = 5)
	private Long id;
	
	private String movieCode;
	
	private String movieName;
	
	private String originalName;
	
	private Integer duration;
	
	private String regia;
	
	private String sceneggiatura;
	
	private String star;
	
	private String movieCast;
	
	private Integer movieYear;
	
	private String plot;
	
	private Float rating;
	
	private Long numRatings;
	
	private Long numFavourites;
	
	private Integer metascore;
	
	private Integer currentPosition;
	
	@OneToMany(targetEntity=WebappReview.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="movie")
	private List<WebappReview> webappReviews =new ArrayList<>();
	
	
	@OneToMany(targetEntity=ImdbReview.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="movie")
	private List<ImdbReview> imdbReviews =new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	
	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
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

	public String getSceneggiatura() {
		return sceneggiatura;
	}

	public void setSceneggiatura(String sceneggiatura) {
		this.sceneggiatura = sceneggiatura;
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

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Long getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(Long numRatings) {
		this.numRatings = numRatings;
	}

	public Long getNumFavourites() {
		return numFavourites;
	}

	public void setNumFavourites(Long numFavourites) {
		this.numFavourites = numFavourites;
	}

	public Integer getMetascore() {
		return metascore;
	}

	public void setMetascore(Integer metascore) {
		this.metascore = metascore;
	}

	public Integer getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Integer currentPosition) {
		this.currentPosition = currentPosition;
	}


	public String getMovieName() {
		return movieName;
	}


	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	public String getMovieCast() {
		return movieCast;
	}


	public void setMovieCast(String movieCast) {
		this.movieCast = movieCast;
	}


	public Integer getMovieYear() {
		return movieYear;
	}


	public void setMovieYear(Integer movieYear) {
		this.movieYear = movieYear;
	}


	public List<WebappReview> getWebappReviews() {
		return webappReviews;
	}


	public void setWebappReviews(List<WebappReview> webappReviews) {
		this.webappReviews = webappReviews;
	}


	public String getMovieCode() {
		return movieCode;
	}


	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}


	public List<ImdbReview> getImdbReviews() {
		return imdbReviews;
	}


	public void setImdbReviews(List<ImdbReview> imdbReviews) {
		this.imdbReviews = imdbReviews;
	}
	
	
	

}
