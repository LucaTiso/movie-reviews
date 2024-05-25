package com.luca.moviereviews.webapp.controllers;


import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luca.moviereviews.core.security.AuthenticationRequest;
import com.luca.moviereviews.core.security.AuthenticationResponse;
import com.luca.moviereviews.core.security.RegisterRequest;
import com.luca.moviereviews.webapp.config.AuthenticationService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationSerivce;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) throws MessagingException{
		authenticationSerivce.register(request);
		return ResponseEntity.accepted().build();
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
		return ResponseEntity.ok(authenticationSerivce.authenticate(request));
	}
	
	@GetMapping("/activate-account")
	public void confirm(@RequestParam String token) throws MessagingException {
		
		authenticationSerivce.activateAccount(token);
	}
	
	
	@PostMapping("/refresh-token")
	public void refreshToken(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		authenticationSerivce.refreshToken(request,response);
		
	}
	
}
