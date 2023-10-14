package com.luca.moviereviews.core.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.luca.moviereviews.core.requests.MovieRequest;
import com.luca.moviereviews.core.requests.WebappReviewRequest;
import com.luca.moviereviews.core.requests.WebappUserRegistration;
import com.luca.moviereviews.jpa.entities.Movie;
import com.luca.moviereviews.jpa.entities.WebappReview;
import com.luca.moviereviews.jpa.entities.WebappUser;
import com.luca.moviereviews.responses.MovieResponse;
import com.luca.moviereviews.responses.ReviewResponse;
import com.luca.moviereviews.responses.UserResponse;

public class EntityUtils {

	private EntityUtils() {
	}

	public static Movie dtoToEntity(MovieRequest movieRequest) {
		Movie movie = new Movie();

		movie.setDuration(movieRequest.getDuration());
		movie.setMetascore(movieRequest.getMetascore());
		movie.setMovieCast(movieRequest.getMovieCast());
		movie.setMovieName(movieRequest.getMovieName());
		movie.setMovieYear(movieRequest.getMovieYear());
		movie.setNumFavourites(movieRequest.getNumFavourites());
		movie.setNumRatings(movieRequest.getNumRatings());
		movie.setOriginalName(movieRequest.getOriginalName());
		movie.setPlot(movieRequest.getPlot());
		movie.setRating(movieRequest.getRating());
		movie.setRegia(movieRequest.getRegia());
		movie.setSceneggiatura(movieRequest.getSceneggiatura());
		movie.setStar(movieRequest.getStar());
		movie.setMovieCode(movieRequest.getMovieCode());

		return movie;

	}

	public static MovieResponse entityToDto(Movie movie) {
		MovieResponse movieResponse = new MovieResponse();

		movieResponse.setTitle(movie.getMovieName());
		movieResponse.setDuration(movie.getDuration());
		movieResponse.setCurrentPosition(movie.getCurrentPosition());
		movieResponse.setMetascore(movie.getMetascore());
		movieResponse.setMovieCast(movie.getMovieCast());
		movieResponse.setMovieCode(movie.getMovieCode());
		movieResponse.setNumRatings(movie.getNumRatings());
		movieResponse.setOriginalTitle(movie.getOriginalName());
		movieResponse.setPlot(movie.getPlot());
		movieResponse.setRating(movie.getRating());
		movieResponse.setRegia(movie.getRegia());
		movieResponse.setSceneggiatura(movie.getSceneggiatura());
		movieResponse.setStar(movie.getStar());
		movieResponse.setYear(movie.getMovieYear());
		movieResponse.setId(movie.getId());

		// todo
		return movieResponse;

	}

	public static WebappUser dtoToEntity(WebappUserRegistration registration) {
		WebappUser webappUser = new WebappUser();

		webappUser.setCountry(registration.getCountry());
		webappUser.setEmail(registration.getEmail());
		webappUser.setLastUpdateTime(LocalDateTime.now());
		webappUser.setPassword(registration.getPassword());
		webappUser.setRegistrationTime(webappUser.getLastUpdateTime());
		webappUser.setUsername(registration.getUsername());

		return webappUser;
	}

	public static UserResponse entityToDto(WebappUser entity) {
		UserResponse userResponse = new UserResponse();
		userResponse.setUsername(entity.getUsername());
		userResponse.setCountry(entity.getCountry());
		userResponse.setEmail(entity.getEmail());
		return userResponse;

	}

	public static WebappReview dtoToEntity(WebappReviewRequest webappReviewRequest) {

		WebappReview webappReview = new WebappReview();

		webappReview.setRating(webappReviewRequest.getRating());
		webappReview.setReviewDate(LocalDate.now());
		webappReview.setReviewTime(LocalDateTime.now());
		webappReview.setText(webappReviewRequest.getText());
		webappReview.setTitle(webappReviewRequest.getTitle());

		return webappReview;
	}

	public static ReviewResponse entityToDto(WebappReview webappReview) {

		ReviewResponse reviewResponse = new ReviewResponse();

		reviewResponse.setId(webappReview.getId());
		reviewResponse.setReviewDate(webappReview.getReviewDate());
		reviewResponse.setReviewTime(webappReview.getReviewTime());
		reviewResponse.setText(webappReview.getText());
		reviewResponse.setTitle(webappReview.getTitle());

		return reviewResponse;
	}

}
