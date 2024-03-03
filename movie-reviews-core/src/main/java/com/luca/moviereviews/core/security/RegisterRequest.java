package com.luca.moviereviews.core.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	private String firstname;
	
	private String lastname;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private String country;
	
	

}
