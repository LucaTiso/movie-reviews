package com.luca.moviereviews.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luca.moviereviews.jpa.entities.Country;

public interface CountryRepository extends JpaRepository<Country,String> {
	
	public List<Country>findAllByOrderByNameAsc();
	
}
