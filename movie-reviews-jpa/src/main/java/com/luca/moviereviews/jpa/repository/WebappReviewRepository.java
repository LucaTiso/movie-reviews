package com.luca.moviereviews.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.luca.moviereviews.jpa.entities.WebappReview;

public interface WebappReviewRepository extends JpaRepository<WebappReview, Long>, JpaSpecificationExecutor<WebappReview> {
	
	Page<WebappReview> findAll(Specification<WebappReview> spec, Pageable pageable);


}
