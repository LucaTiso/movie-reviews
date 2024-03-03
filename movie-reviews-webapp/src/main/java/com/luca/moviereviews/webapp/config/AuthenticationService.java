package com.luca.moviereviews.webapp.config;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luca.moviereviews.core.security.AuthenticationRequest;
import com.luca.moviereviews.core.security.AuthenticationResponse;
import com.luca.moviereviews.core.security.JwtService;
import com.luca.moviereviews.core.security.RegisterRequest;
import com.luca.moviereviews.core.security.UserDetailsImpl;
import com.luca.moviereviews.jpa.entities.SecurityUser;
import com.luca.moviereviews.jpa.enums.Role;
import com.luca.moviereviews.jpa.repository.SecurityUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	
	private final SecurityUserRepository repository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest request) {
		var user = UserDetailsImpl.builder().firstName(request.getFirstname()).lastName(request.getLastname())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).username(request.getUsername())
				.role(Role.USER)
				.build();
		
		
		SecurityUser securityUser = new SecurityUser();
		
		securityUser.setUsername(user.getUsername());
		securityUser.setCountry(user.getCountry());
		securityUser.setEmail(user.getEmail());
		securityUser.setFirstName(user.getFirstName());
		securityUser.setLastName(user.getLastName());
		securityUser.setPassword(user.getPassword());
		securityUser.setRole(user.getRole());
		securityUser.setActive(false);
		repository.save(securityUser);
		
		
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	@Transactional
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		SecurityUser securityUser = repository.findByUsername(request.getUsername()).orElseThrow();
		securityUser.setActive(true);
		
		UserDetails user=UserDetailsImpl.builder().firstName(securityUser.getFirstName()).lastName(securityUser.getLastName())
		.email(securityUser.getEmail()).password(securityUser.getPassword())
		.role(securityUser.getRole()).username(securityUser.getUsername())
		.build();
		
		
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}
	
	
	@Transactional
	public void editProfile(RegisterRequest request ) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		SecurityUser securityUser = repository.findByUsername(userDetails.getUsername()).orElseThrow();
		if(request.getCountry()!=null) {
			securityUser.setCountry(request.getCountry());
		}
		if(request.getEmail()!=null) {
			securityUser.setEmail(request.getEmail());
		}
		if(request.getFirstname()!=null) {
			securityUser.setFirstName(request.getFirstname());
		}
		if(request.getLastname()!=null) {
			securityUser.setLastName(request.getLastname());
		}
		if(request.getPassword()!=null) {
			securityUser.setPassword(request.getPassword());
		}
		if(request.getUsername()!=null) {
			securityUser.setUsername(request.getUsername());
		}
		securityUser.setActive(false);
		
	}
}
