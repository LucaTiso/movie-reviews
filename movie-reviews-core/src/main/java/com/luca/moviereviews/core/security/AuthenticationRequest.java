package com.luca.moviereviews.core.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {
	
	@NotEmpty(message="Username is mandatory")
	@NotBlank(message="Username is mandatory")
    private String username;
	
	
	@NotEmpty(message="Password is mandatory")
	@NotBlank(message="Password is mandatory")
	private String password;

}
