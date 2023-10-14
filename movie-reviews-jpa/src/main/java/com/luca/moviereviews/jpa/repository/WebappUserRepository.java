package com.luca.moviereviews.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luca.moviereviews.jpa.entities.WebappUser;

public interface WebappUserRepository extends JpaRepository<WebappUser,Long> {
	
	Optional<WebappUser> findByUsername(String username);
}
