package com.luca.moviereviews.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luca.moviereviews.jpa.entities.WebappReview;

public interface WebappReviewRepository extends JpaRepository<WebappReview, Long> {

	Page<WebappReview> findPaginatedByMovieId(Specification<WebappReview> spec, Pageable pageable);

}
