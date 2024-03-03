package com.luca.moviereviews.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luca.moviereviews.jpa.entities.SecurityUser;

public interface SecurityUserRepository extends JpaRepository<SecurityUser,Long> {
	
	Optional<SecurityUser> findByUsername(String username);
	
	@Modifying
	@Query("DELETE FROM FavouritesMapping m where m.webappUser.id = :userId and m.movie.id in :movieIdList")
	public void deleteUserFavourite(@Param("userId") Long userId,@Param("movieIdList") List<Long> movieIdList);
	
}
