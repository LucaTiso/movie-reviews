package com.luca.moviereviews.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luca.moviereviews.jpa.entities.FavouritesMapping;
import com.luca.moviereviews.jpa.entities.FavouritesMappingId;

public interface FavouritesRepository extends JpaRepository<FavouritesMapping,FavouritesMappingId> {
	
	@Query("from FavouritesMapping m where m.id.webappUserId =:userId")
	List<FavouritesMapping> findByUserId(@Param("userId")Long userId);
	

}
