package com.luca.moviereviews.jpa.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FavouritesMappingId implements Serializable {

	private static final long serialVersionUID = 8031158826175994637L;

	@Column(name = "WEBAPP_USER_ID")
	private Long webappUserId;

	@Column(name = "MOVIE_ID")
	private Long movieId;
	
	public FavouritesMappingId() {
		
	}
	
	 public FavouritesMappingId(Long webappUserId, Long movieId) {
         this.webappUserId = webappUserId;
         this.movieId = movieId;
     }

	public Long getWebappUserId() {
		return webappUserId;
	}

	public void setWebappUserId(Long webappUserId) {
		this.webappUserId = webappUserId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

}
