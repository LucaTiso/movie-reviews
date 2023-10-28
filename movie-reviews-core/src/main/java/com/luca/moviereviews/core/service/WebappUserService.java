package com.luca.moviereviews.core.service;

import java.util.List;

import com.luca.moviereviews.core.requests.WebappUserLogin;
import com.luca.moviereviews.core.requests.WebappUserRegistration;
import com.luca.moviereviews.core.requests.WebappUserRequest;
import com.luca.moviereviews.responses.UserResponse;

public interface WebappUserService {

	public void registration(WebappUserRegistration registration);

	public UserResponse editProfile(WebappUserRequest webappUserReqest);

	public UserResponse login(WebappUserLogin login);

	public void addFavourite(Long movieId, String username);

	public void removeFromFavourites(Long movieId, String username);

	public void removeFromFavourites(List<Long> movieIdList, String username);
	
	public void searchFavourites(String username);

}
