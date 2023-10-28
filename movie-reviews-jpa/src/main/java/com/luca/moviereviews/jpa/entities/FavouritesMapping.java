package com.luca.moviereviews.jpa.entities;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "FAVOURITES_MAPPING")
public class FavouritesMapping {

	@EmbeddedId
	private FavouritesMappingId id;

	@ManyToOne
	@MapsId("webappUserId")
	@JoinColumn(name = "WEBAPP_USER_ID")
	private WebappUser webappUser;

	@ManyToOne
	@MapsId("movieId")
	@JoinColumn(name = "MOVIE_ID")
	private Movie movie;
	
	public FavouritesMapping() {
		
	}

	public FavouritesMapping(FavouritesMappingId id) {
		this.id = id;
	}

	public WebappUser getWebappUser() {
		return webappUser;
	}

	public void setWebappUser(WebappUser webappUser) {

		this.webappUser = webappUser;

	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {

		this.movie = movie;
	}

	public FavouritesMappingId getId() {
		return id;
	}

	public void setId(FavouritesMappingId id) {
		this.id = id;
	}

}
