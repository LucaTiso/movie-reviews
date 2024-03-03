package com.luca.moviereviews.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "FAVOURITES_MAPPING")
public class FavouritesMapping {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FAVOURITES_MAPPING_SEQ")
	@SequenceGenerator(name = "FAVOURITES_MAPPING_SEQ", sequenceName = "FAVOURITES_MAPPING_SEQ", allocationSize = 5)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "WEBAPP_USER_ID")
	private SecurityUser webappUser;

	@ManyToOne
	@JoinColumn(name = "MOVIE_ID")
	private Movie movie;
	
	
	public SecurityUser getWebappUser() {
		return webappUser;
	}

	public void setWebappUser(SecurityUser webappUser) {
		this.webappUser = webappUser;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {

		this.movie = movie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}
