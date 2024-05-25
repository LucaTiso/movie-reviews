package com.luca.moviereviews.jpa.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
	

	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOKEN_SEQ")
	@SequenceGenerator(name = "TOKEN_SEQ", sequenceName = "TOKEN_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name="TOKEN")
	private String token;
		
	@Column(name="CREATED_AT")
	private LocalDateTime createdAt;
	
	@Column(name="EXPIRES_AT")
	private LocalDateTime expiresAt;
	
	@Column(name="VALIDATED_AT")
	private LocalDateTime validatedAt;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private SecurityUser securityUser;
	
}
