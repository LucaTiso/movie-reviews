package com.luca.moviereviews.webapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.moviereviews.core.requests.ChangeEmailRequest;
import com.luca.moviereviews.core.requests.ChangePasswordRequest;
import com.luca.moviereviews.core.requests.EditProfileRequest;
import com.luca.moviereviews.core.service.WebappUserService;
import com.luca.moviereviews.responses.FavouriteMappingResponse;
import com.luca.moviereviews.responses.UserResponse;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/api/webapp-users")
public class WebappUserController {

	@Autowired
	private WebappUserService webappUserService;

	
	@PatchMapping(path = "/changePassword")
	public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest webappUserRequest,Principal connectedUser) {

		webappUserService.changePassword(webappUserRequest,connectedUser);
		ResponseEntity<Void> response = new ResponseEntity<>( HttpStatus.OK);		
		return response;

	}
	

	@PatchMapping(path = "/changeEmail")
	public ResponseEntity<Void> changeEmail(@RequestBody ChangeEmailRequest webappUserRequest,Principal connectedUser) {

		webappUserService.changeEmail(webappUserRequest,connectedUser);
		ResponseEntity<Void> response = new ResponseEntity<>( HttpStatus.OK);		
		return response;

	}
	
	
	@PatchMapping(path = "/editProfile")
	public ResponseEntity<Void> editProfile(@RequestBody EditProfileRequest webappUserRequest,Principal connectedUser) {

		webappUserService.editProfile(webappUserRequest,connectedUser);
		ResponseEntity<Void> response = new ResponseEntity<>( HttpStatus.OK);	
		return response;

	}
	

	@GetMapping(path = "/getProfile")
	public ResponseEntity<?> getProfile(Principal connectedUser) {
		
	
		UserResponse pResponse=webappUserService.getProfile(connectedUser);
		ResponseEntity<?> response = new ResponseEntity<>(pResponse, HttpStatus.OK);	
		return response;

	}

	
	@PutMapping(path = "/favourites/{id}")
	public ResponseEntity<?> addFavourite(@PathVariable Long id) {

		this.webappUserService.addFavourite(id, "giampierpaolo");
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	
	@DeleteMapping(path = "/favourites")
	public ResponseEntity<?> deleteFavourite(@RequestBody List<Long> moveIdList) {


		
		webappUserService.removeFromFavourites(moveIdList,"pierpecco");

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	
	@GetMapping(path = "/favourites")
	public ResponseEntity<?> retrieveFavourite() {

		List<FavouriteMappingResponse> responseList=webappUserService.searchFavourites("isisisi");
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/favourites/download")
	public ResponseEntity<Void> downloadFavourites(HttpServletResponse  response) {

		try(PrintWriter writer=response.getWriter()){
			this.webappUserService.downloadFavourites(writer);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
