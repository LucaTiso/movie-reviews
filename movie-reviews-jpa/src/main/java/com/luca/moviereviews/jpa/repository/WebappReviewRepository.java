package com.luca.moviereviews.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luca.moviereviews.jpa.entities.WebappReview;

public interface WebappReviewRepository extends JpaRepository<WebappReview, Long>, JpaSpecificationExecutor<WebappReview> {
	
	Page<WebappReview> findAll(Specification<WebappReview> spec, Pageable pageable);
	
	
	@Query("FROM WebappReview r where r.movie.id =:movieId and r.webappUser.username =:username")
	Optional<WebappReview> findByMovieAndUsername(@Param("movieId") Long movieId,@Param("username") String username);


}
