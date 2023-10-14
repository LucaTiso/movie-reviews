package com.luca.moviereviews.core.service;

import com.luca.moviereviews.core.requests.WebappUserLogin;
import com.luca.moviereviews.core.requests.WebappUserRegistration;
import com.luca.moviereviews.core.requests.WebappUserRequest;
import com.luca.moviereviews.responses.UserResponse;

public interface WebappUserService {
	
	public void registration(WebappUserRegistration registration);
	
	public UserResponse editProfile(WebappUserRequest webappUserReqest);
	
	public UserResponse login(WebappUserLogin login);

}
