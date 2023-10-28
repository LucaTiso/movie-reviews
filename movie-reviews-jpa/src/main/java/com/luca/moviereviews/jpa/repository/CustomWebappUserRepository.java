package com.luca.moviereviews.jpa.repository;

import com.luca.moviereviews.jpa.entities.Movie;
import com.luca.moviereviews.jpa.entities.WebappUser;

public interface CustomWebappUserRepository {

	public void addFavourite(WebappUser user, Movie movie);

}
