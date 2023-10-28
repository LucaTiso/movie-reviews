package com.luca.moviereviews.webapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luca.moviereviews.core.requests.WebappUserLogin;
import com.luca.moviereviews.core.requests.WebappUserRegistration;
import com.luca.moviereviews.core.requests.WebappUserRequest;
import com.luca.moviereviews.core.service.WebappUserService;
import com.luca.moviereviews.responses.UserResponse;
import com.luca.moviereviews.responses.UserResponseTest;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/webapp-users")
public class WebappUserController {

	@Autowired
	private WebappUserService webappUserService;

	@CrossOrigin
	@PostMapping(path = "/registration")
	public ResponseEntity<?> registration(@Valid @RequestBody WebappUserRegistration webappUserRegistration) {

		webappUserService.registration(webappUserRegistration);
		ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.OK);
		return response;

	}

	@CrossOrigin
	@PatchMapping(path = "/editProfile")
	public ResponseEntity<UserResponse> editProfile(@RequestBody WebappUserRequest webappUserRequest) {

		UserResponse userResponse = webappUserService.editProfile(webappUserRequest);

		ResponseEntity<UserResponse> response = userResponse != null ? new ResponseEntity<>(userResponse, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return response;

	}

	@CrossOrigin
	@PostMapping(path = "/login")
	public ResponseEntity<UserResponse> login(@RequestBody WebappUserLogin webappuserLogin) {

		UserResponse userResponse = webappUserService.login(webappuserLogin);
		ResponseEntity<UserResponse> response;

		response = userResponse != null ? new ResponseEntity<>(userResponse, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return response;

	}

	@CrossOrigin
	@PutMapping(path = "/favourites/{id}")
	public ResponseEntity<?> addFavourite(@PathVariable Long id, @RequestHeader("username") String username) {

		this.webappUserService.addFavourite(id, username);

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@CrossOrigin
	@DeleteMapping(path = "/favourites/{id}")
	public ResponseEntity<?> deleteFavourite(@PathVariable Long id, @RequestHeader("username") String username) {

		this.webappUserService.removeFromFavourites(id, username);

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@CrossOrigin
	@DeleteMapping(path = "/favourites")
	public ResponseEntity<?> deleteFavourite(@RequestBody List<Long> moveIdList,
			@RequestHeader("username") String username) {

		System.out.println("id list: ");
		
		moveIdList.forEach(System.out::println);
		webappUserService.removeFromFavourites(moveIdList, username);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@GetMapping(path = "/favourites")
	public ResponseEntity<?> retrieveFavourite(@RequestHeader("username") String username) {

		
		webappUserService.searchFavourites(username);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(path = "/getUserTest")
	public ResponseEntity<?> registration(@RequestParam("name") String name) {

		UserResponseTest test = new UserResponseTest();
		test.setAge(50);

		test.setName(name != null && name.length() > 0 ? name : "vuoto");
		test.setSurname("Pippo");

		System.out.println("chiamato " + System.currentTimeMillis() + " param1: " + name);

		ResponseEntity<?> response = new ResponseEntity<>(test, HttpStatus.OK);

		return response;

	}

}
