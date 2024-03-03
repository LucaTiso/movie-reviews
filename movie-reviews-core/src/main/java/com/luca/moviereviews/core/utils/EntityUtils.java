package com.luca.moviereviews.core.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.luca.moviereviews.core.requests.MovieRequest;
import com.luca.moviereviews.core.requests.WebappReviewRequest;
import com.luca.moviereviews.core.requests.WebappUserRegistration;
import com.luca.moviereviews.jpa.entities.FavouritesMapping;
import com.luca.moviereviews.jpa.entities.MetacriticUserReview;
import com.luca.moviereviews.jpa.entities.Movie;
import com.luca.moviereviews.jpa.entities.SecurityUser;
import com.luca.moviereviews.jpa.entities.WebappReview;
import com.luca.moviereviews.responses.FavouriteMappingResponse;
import com.luca.moviereviews.responses.MetacriticReviewResponse;
import com.luca.moviereviews.responses.MovieResponse;
import com.luca.moviereviews.responses.WebappReviewResponse;
import com.luca.moviereviews.responses.UserResponse;

public class EntityUtils {

	private EntityUtils() {
	}

	public static Movie dtoToEntity(MovieRequest movieRequest) {
		Movie movie = new Movie();

		movie.setDuration(movieRequest.getDuration());
		movie.setMetascore(movieRequest.getMetascore());
		movie.setPlot(movieRequest.getPlot());
		movie.setRegia(movieRequest.getRegia());
		movie.setStar(movieRequest.getStar());
		movie.setYear(movieRequest.getYear());
		
		movie.setMovieCast(movieRequest.getCast());
		
		movie.setGenre(movieRequest.getGenre());
		movie.setHref(movieRequest.getHref());
		movie.setMetascore(movieRequest.getMetascore());
		movie.setMetascoreNumRatings(movieRequest.getMetascoreNumRatings());
		movie.setMovieRatingCategory(movieRequest.getMovieRatingCategory());
		movie.setProduction(movieRequest.getProduction());
		
		movie.setTitle(movieRequest.getTitle());
		movie.setUserNumRatings(movieRequest.getUserNumRatings());
		movie.setUserRating(movieRequest.getUserRating());
		
		
		return movie;

	}

	public static MovieResponse entityToDto(Movie movie) {
		MovieResponse movieResponse = new MovieResponse();

		movieResponse.setTitle(movie.getTitle());
		movieResponse.setDuration(movie.getDuration());
		movieResponse.setMetascore(movie.getMetascore());
		movieResponse.setCast(movie.getMovieCast());
		movieResponse.setHref(movie.getHref());
		movieResponse.setUserNumRatings(movie.getUserNumRatings());
		
		movieResponse.setPlot(movie.getPlot());
		movieResponse.setUserRating(movie.getUserRating());
		movieResponse.setRegia(movie.getRegia());
		
		movieResponse.setStar(movie.getStar());
		movieResponse.setYear(movie.getYear());
		movieResponse.setId(movie.getId());
		
		movieResponse.setGenre(movie.getGenre());
		movieResponse.setMetascoreNumRatings(movie.getMetascoreNumRatings());
		movieResponse.setMovieRatingCategory(movie.getMovieRatingCategory());
		
		movieResponse.setProduction(movie.getProduction());
		movieResponse.setUserNumRatings(movie.getUserNumRatings());
		movieResponse.setUserRating(movie.getUserRating());
		
		movieResponse.setYear(movie.getYear());
		
	
		// todo
		return movieResponse;

	}

	public static SecurityUser dtoToEntity(WebappUserRegistration registration) {
		SecurityUser webappUser = new SecurityUser();

		webappUser.setCountry(registration.getCountry());
		webappUser.setEmail(registration.getEmail());
		webappUser.setLastUpdateTime(LocalDateTime.now());
		webappUser.setPassword(registration.getPassword());
		webappUser.setRegistrationTime(webappUser.getLastUpdateTime());
		webappUser.setUsername(registration.getUsername());

		return webappUser;
	}

	public static UserResponse entityToDto(SecurityUser entity) {
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
		webappReview.setUsername(webappReviewRequest.getUsername());

		return webappReview;
	}

	public static WebappReviewResponse entityToDto(WebappReview webappReview) {

		WebappReviewResponse reviewResponse = new WebappReviewResponse();

		reviewResponse.setId(webappReview.getId());
		reviewResponse.setReviewDate(webappReview.getReviewDate());
		reviewResponse.setReviewTime(webappReview.getReviewTime());
		reviewResponse.setText(webappReview.getText());
	
		reviewResponse.setRating(webappReview.getRating());
		reviewResponse.setUsername(webappReview.getUsername());
		reviewResponse.setReviewerId(webappReview.getWebappUser().getId());

		return reviewResponse;
	}
	
	public static MetacriticReviewResponse entityToDto(MetacriticUserReview metacriticuserReview) {

		MetacriticReviewResponse reviewResponse = new MetacriticReviewResponse();

		reviewResponse.setId(metacriticuserReview.getId());
		reviewResponse.setRating(metacriticuserReview.getRating());
		reviewResponse.setReviewDate(metacriticuserReview.getReviewDate());
		reviewResponse.setText(metacriticuserReview.getText());
		reviewResponse.setUsername(metacriticuserReview.getUsername());

		return reviewResponse;
	}
	
	public static FavouriteMappingResponse entityToDto(FavouritesMapping entity){
		FavouriteMappingResponse response=new FavouriteMappingResponse();
		response.setPosition(0);
		response.setMovieResponse(entityToDto(entity.getMovie()));
		return response;
		
	}
	
	

}
