package com.luca.moviereviews.core.service;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import com.luca.moviereviews.core.requests.ChangeEmailRequest;
import com.luca.moviereviews.core.requests.ChangePasswordRequest;
import com.luca.moviereviews.core.requests.EditProfileRequest;
import com.luca.moviereviews.responses.FavouriteMappingResponse;
import com.luca.moviereviews.responses.UserResponse;


public interface WebappUserService {

	public void changePassword(ChangePasswordRequest changePassowrdRequest,Principal principal);
	
	public void changeEmail(ChangeEmailRequest changeEmailRequest,Principal principal);
	
	public void editProfile(EditProfileRequest editProfileRequest,Principal principal);
	
	public void addFavourite(Long movieId, String username);

	public void removeFromFavourites(List<Long> movieIdList, String username);
	
	public List<FavouriteMappingResponse> searchFavourites(String username);
	
	public void downloadFavourites(PrintWriter outputStream);
	
	public UserResponse getProfile(Principal principal);
}
