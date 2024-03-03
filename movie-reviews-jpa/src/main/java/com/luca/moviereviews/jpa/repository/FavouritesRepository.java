package com.luca.moviereviews.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.luca.moviereviews.jpa.entities.FavouritesMapping;

public interface FavouritesRepository extends JpaRepository<FavouritesMapping,Long> {
	
	@Query("from FavouritesMapping m where m.webappUser.id =:userId")
	List<FavouritesMapping> findByUserId(@Param("userId")Long userId);
	
	
	@Modifying
	@Query("DELETE FROM FavouritesMapping m where m.webappUser.id = :userId and m.id in :favouritesIdList")
	public void deleteUserFavourite(@Param("userId") Long userId,@Param("favouritesIdList") List<Long> movieIdList);

}
