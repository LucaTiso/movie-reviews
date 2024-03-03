package com.luca.moviereviews.webapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luca.moviereviews.core.requests.WebappUserRequest;
import com.luca.moviereviews.core.service.WebappUserService;
import com.luca.moviereviews.responses.FavouriteMappingResponse;
import com.luca.moviereviews.responses.UserResponse;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/api/webapp-users")
public class WebappUserController {

	@Autowired
	private WebappUserService webappUserService;

	@CrossOrigin
	@PatchMapping(path = "/editProfile")
	public ResponseEntity<UserResponse> editProfile(@RequestBody WebappUserRequest webappUserRequest) {

		UserResponse userResponse = webappUserService.editProfile(webappUserRequest);

		ResponseEntity<UserResponse> response = userResponse != null ? new ResponseEntity<>(userResponse, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return response;

	}

	@CrossOrigin
	@PutMapping(path = "/favourites/{id}")
	public ResponseEntity<?> addFavourite(@PathVariable Long id) {

		this.webappUserService.addFavourite(id, "giampierpaolo");
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	


	@CrossOrigin
	@DeleteMapping(path = "/favourites")
	public ResponseEntity<?> deleteFavourite(@RequestBody List<Long> moveIdList) {

		System.out.println("id list: ");

		moveIdList.forEach(System.out::println);
		webappUserService.removeFromFavourites(moveIdList,"pierpecco");

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(path = "/favourites")
	public ResponseEntity<?> retrieveFavourite() {

		List<FavouriteMappingResponse> responseList=webappUserService.searchFavourites("isisisi");
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@CrossOrigin
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
