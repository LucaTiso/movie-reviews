package com.luca.moviereviews.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.luca.moviereviews.jpa.entities.MovieSearch;

public interface MovieSearchRepository extends JpaRepository<MovieSearch,Long>, JpaSpecificationExecutor<MovieSearch> {
	
	
	
}
