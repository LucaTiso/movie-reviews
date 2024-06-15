package com.luca.moviereviews.core.security;


import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Getter
@Setter
@Builder
public class RegisterRequest {
	
	@NotEmpty(message="Firstname is mandatory")
	@NotBlank(message="Firstname is mandatory")
	private String firstname;
	
	@NotEmpty(message="Lastname is mandatory")
	@NotBlank(message="Lastname is mandatory")
	private String lastname;
	
	@NotEmpty(message="Username is mandatory")
	@NotBlank(message="Username is mandatory")
	private String username;
	
	@NotEmpty(message="Password is mandatory")
	@NotBlank(message="Password is mandatory")
	@Size(min=8, message="Password should be 8 characters long minimum")
	private String password;
	
	@NotEmpty(message="Email is mandatory")
	@NotBlank(message="Email is mandatory")
	@Email(message="email is not formatted")
	private String email;
	
	@NotEmpty(message="Country is mandatory")
	@NotBlank(message="Country is mandatory")
	private String country;
	
	

}
