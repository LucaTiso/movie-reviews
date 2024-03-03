package com.luca.moviereviews.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.luca.moviereviews.jpa.entities.Movie;


public interface MovieRepository extends JpaRepository<Movie,Long>, JpaSpecificationExecutor<Movie>{
	
	
	
}
