package com.luca.moviereviews.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luca.moviereviews.jpa.entities.ImdbReview;

public interface ImdbReviewRepository extends JpaRepository<ImdbReview,Long>{

}
