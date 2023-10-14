package com.luca.moviereviews.jpa.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class WebappUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WEBAPP_USER_SEQ")
	@SequenceGenerator(name = "WEBAPP_USER_SEQ", sequenceName = "WEBAPP_USER_SEQ", allocationSize = 5)
	private Long id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private LocalDateTime registrationTime;
	
	private LocalDateTime lastUpdateTime;
	
	private String country;
	
	
	@OneToMany(targetEntity=WebappReview.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="webappUser")
	private List<WebappReview> webappReviews=new ArrayList<>();


	public Long getId() {
		return id;
	}


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


	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}


	public void setRegistrationTime(LocalDateTime registrationTime) {
		this.registrationTime = registrationTime;
	}


	public LocalDateTime getLastUpdateTime() {
		return lastUpdateTime;
	}


	public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public List<WebappReview> getWebappReviews() {
		return webappReviews;
	}


	public void setWebappReviews(List<WebappReview> webappReviews) {
		this.webappReviews = webappReviews;
	}
	
}
