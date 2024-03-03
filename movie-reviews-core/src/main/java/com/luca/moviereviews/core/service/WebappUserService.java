package com.luca.moviereviews.core.service;

import java.io.PrintWriter;
import java.util.List;

import com.luca.moviereviews.core.requests.WebappUserRequest;
import com.luca.moviereviews.responses.FavouriteMappingResponse;
import com.luca.moviereviews.responses.UserResponse;

public interface WebappUserService {

	public UserResponse editProfile(WebappUserRequest webappUserReqest);
	
	public void addFavourite(Long movieId, String username);

	public void removeFromFavourites(List<Long> movieIdList, String username);
	
	public List<FavouriteMappingResponse> searchFavourites(String username);
	
	public void downloadFavourites(PrintWriter outputStream);
}
