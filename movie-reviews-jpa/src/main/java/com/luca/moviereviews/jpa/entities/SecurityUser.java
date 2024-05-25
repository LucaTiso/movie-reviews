package com.luca.moviereviews.jpa.entities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.luca.moviereviews.jpa.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="SECURITY_USER")
public class SecurityUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECURITY_USER_SEQ")
	@SequenceGenerator(name = "SECURITY_USER_SEQ", sequenceName = "SECURITY_USER_SEQ", allocationSize = 5)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SECURITY_ROLE")
	private Role role;
	
	@Column(name="USERNAME")
	private String username;

	@Column(name="EMAIL")
	private String email;

	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="REGISTRATION_TIME")
	private LocalDateTime registrationTime;

	@Column(name="LAST_UPDATE_TIME")
	private LocalDateTime lastUpdateTime;
	
	@Column(name="ENABLED")
	private Boolean enabled;
	
	@OneToMany(targetEntity = WebappReview.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "webappUser")
	private List<WebappReview> webappReviews = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public List<WebappReview> getWebappReviews() {
		return webappReviews;
	}

	public void setWebappReviews(List<WebappReview> webappReviews) {
		this.webappReviews = webappReviews;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
