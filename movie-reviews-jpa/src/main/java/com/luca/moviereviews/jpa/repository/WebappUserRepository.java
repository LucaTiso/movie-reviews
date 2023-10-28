package com.luca.moviereviews.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luca.moviereviews.jpa.entities.WebappUser;

public interface WebappUserRepository extends JpaRepository<WebappUser, Long>,CustomWebappUserRepository {

	public Optional<WebappUser> findByUsername(String username);
	
	
	@Modifying
	@Query("DELETE FROM FavouritesMapping m where m.id.webappUserId = :userId and m.id.movieId = :movieId")
	public void deleteUserFavourite(@Param("userId") Long userId,@Param("movieId") Long movieId);
	
	
	@Modifying
	@Query("DELETE FROM FavouritesMapping m where m.id.webappUserId = :userId and m.id.movieId in :movieIdList")
	public void deleteUserFavourite(@Param("userId") Long userId,@Param("movieIdList") List<Long> movieIdList);
	
	
	
	// estendere il repository per poter usare la query jpql
	// DELETE FROM Student s JOIN s.courses c WHERE s.id = :studentId AND c.id =
	// :courseId

	// al posto di questa nativa

	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "DELETE FROM FAVOURITES_MAPPING WHERE webapp_user_id = :userId AND movie_id = :movieId"
	 * , nativeQuery = true) void deleteUserFavourite(@Param("userId") Long
	 * userId,@Param("movieId") Long movieId);
	 * 
	 * @Modifying
	 * 
	 * @Query(value =
	 * "DELETE FROM FAVOURITES_MAPPING WHERE webapp_user_id = :userId AND movie_id in :movieIdList"
	 * , nativeQuery = true) void deleteUserFavourite(@Param("userId") Long
	 * userId,@Param("movieIdList") List<Long> movieIdList);
	 */
}
