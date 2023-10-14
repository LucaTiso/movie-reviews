package com.luca.moviereviews.core.requests;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WebappUserRegistration {

	@NotNull
	@Size(min=5,message="username must be at least 5 characters long")
	private String username;

	@Email(message="invalid email")
	private String email;

	@NotNull
	@Size(min=8,message="password ,ust be at least 8 characters long")
	private String password;
	
	@NotBlank
	private String country;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
