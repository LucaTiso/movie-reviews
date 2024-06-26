package com.luca.moviereviews.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luca.moviereviews.jpa.entities.Token;

public interface TokenRepository extends JpaRepository<Token,Long> {
	
	
	Optional<Token> findByToken(String token);
	

}
