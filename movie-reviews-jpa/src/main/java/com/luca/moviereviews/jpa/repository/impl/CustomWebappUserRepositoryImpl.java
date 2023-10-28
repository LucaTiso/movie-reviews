package com.luca.moviereviews.jpa.repository.impl;

import com.luca.moviereviews.jpa.entities.FavouritesMapping;
import com.luca.moviereviews.jpa.entities.FavouritesMappingId;
import com.luca.moviereviews.jpa.entities.Movie;
import com.luca.moviereviews.jpa.entities.WebappUser;
import com.luca.moviereviews.jpa.repository.CustomWebappUserRepository;

import jakarta.persistence.EntityManager;

public class CustomWebappUserRepositoryImpl implements CustomWebappUserRepository {

	private EntityManager em;

	public CustomWebappUserRepositoryImpl(EntityManager em) {
		this.em = em;

	}

	@Override
	public void addFavourite(WebappUser user, Movie movie) {
		FavouritesMappingId mappingId = new FavouritesMappingId(user.getId(), movie.getId());

		FavouritesMapping mapping = new FavouritesMapping(mappingId);
		mapping.setMovie(movie);
		mapping.setWebappUser(user);

		em.persist(mapping);
	}

}
