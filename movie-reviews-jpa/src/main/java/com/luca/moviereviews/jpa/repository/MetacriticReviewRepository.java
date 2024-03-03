package com.luca.moviereviews.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.luca.moviereviews.jpa.entities.MetacriticUserReview;

public interface MetacriticReviewRepository extends JpaRepository<MetacriticUserReview,Long>, JpaSpecificationExecutor<MetacriticUserReview>{

}
