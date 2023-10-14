package com.luca.moviereviews.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luca.moviereviews.jpa.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long> {
	
	@Query("select max(m.currentPosition) from Movie m")
	Integer findLastMoviePosition();
	
	@Query("from Movie m ORDER BY m.currentPosition DESC")
	List<Movie> findAllMovies();
	
}
