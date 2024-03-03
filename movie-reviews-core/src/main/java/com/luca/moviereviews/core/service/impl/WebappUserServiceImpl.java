package com.luca.moviereviews.core.service.impl;


import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.luca.moviereviews.core.requests.WebappUserRequest;
import com.luca.moviereviews.core.service.WebappUserService;
import com.luca.moviereviews.core.utils.CsvUtils;
import com.luca.moviereviews.core.utils.EntityUtils;
import com.luca.moviereviews.jpa.entities.FavouritesMapping;
import com.luca.moviereviews.jpa.entities.Movie;
import com.luca.moviereviews.jpa.entities.SecurityUser;
import com.luca.moviereviews.jpa.repository.FavouritesRepository;
import com.luca.moviereviews.jpa.repository.MovieRepository;
import com.luca.moviereviews.jpa.repository.SecurityUserRepository;
import com.luca.moviereviews.responses.FavouriteMappingResponse;
import com.luca.moviereviews.responses.UserResponse;

@Service
public class WebappUserServiceImpl implements WebappUserService {

	private final SecurityUserRepository securityUserRepository;

	private final MovieRepository movieRepository;

	private final FavouritesRepository favouritesRepository;

	public WebappUserServiceImpl(SecurityUserRepository securityUserRepository, MovieRepository movieRepository,
			FavouritesRepository favouritesRepository) {
		this.securityUserRepository = securityUserRepository;
		this.movieRepository = movieRepository;
		this.favouritesRepository = favouritesRepository;
	}


	@Override
	@Transactional
	public UserResponse editProfile(WebappUserRequest webappUserReqest) {

		UserResponse userResponse = null;

		SecurityUser user = securityUserRepository.findByUsername(webappUserReqest.getUsername()).orElse(null);
		if (user != null) {
			if (webappUserReqest.getCountry() != null) {
				user.setCountry(webappUserReqest.getCountry());
			}
			if (webappUserReqest.getEmail() != null) {
				user.setEmail(webappUserReqest.getEmail());
			}
			if (webappUserReqest.getPassword() != null) {
				user.setPassword(webappUserReqest.getPassword());
			}
			user.setLastUpdateTime(LocalDateTime.now());
			userResponse = EntityUtils.entityToDto(user);
		}

		return userResponse;

	}

	@Override
	@Transactional
	public void addFavourite(Long movieId, String username) {

		
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		SecurityUser webappuser = securityUserRepository.findByUsername(userDetails.getUsername())
				.orElseThrow(() -> new RuntimeException("utente non trovato"));

		Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("film non trovato"));

		FavouritesMapping mapping = new FavouritesMapping();
		mapping.setMovie(movie);
		mapping.setWebappUser(webappuser);
		favouritesRepository.save(mapping);

	}


	@Override
	@Transactional
	public void removeFromFavourites(List<Long> fovouritesIds, String username) {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SecurityUser webappuser = securityUserRepository.findByUsername(userDetails.getUsername())
				.orElseThrow(() -> new RuntimeException("utente non trovato"));

		securityUserRepository.deleteUserFavourite(webappuser.getId(), fovouritesIds);

	}

	@Override
	@Transactional(readOnly=true)
	public List<FavouriteMappingResponse> searchFavourites(String username) {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SecurityUser webappuser = securityUserRepository.findByUsername(userDetails.getUsername())
				.orElseThrow(() -> new RuntimeException("utente non trovato"));

		// favouritesRepository.findBy
		List<FavouriteMappingResponse> mappingList = favouritesRepository.findByUserId(webappuser.getId())
				.stream().map(EntityUtils::entityToDto).toList();
		
		return mappingList;
		
	}


	@Override
	public void downloadFavourites(PrintWriter printWriter) {
		List<FavouriteMappingResponse> retrievedList=this.searchFavourites("totti");
		
		StringBuilder sb=new StringBuilder("");
		
		sb.append("Position,Title,URL,Production,Star,Cast,Year,Genres,Regia,Duration,Metacritic user rating,Metacritic user num ratings,Metascore,Metascore num ratings,Movie Category\n");
		retrievedList.forEach(m->{
			sb.append(m.getPosition()+","+CsvUtils.escape(m.getMovieResponse().getTitle())+","+CsvUtils.escape(m.getMovieResponse().getHref())+","+CsvUtils.escape(m.getMovieResponse().getProduction())+","+CsvUtils.escape(m.getMovieResponse().getStar())+","+
					CsvUtils.escape(m.getMovieResponse().getCast())+","+m.getMovieResponse().getYear()+","+CsvUtils.escape(m.getMovieResponse().getGenre())+","+CsvUtils.escape(m.getMovieResponse().getRegia())+","+
					m.getMovieResponse().getDuration()+","+m.getMovieResponse().getUserRating()+","+m.getMovieResponse().getUserNumRatings()+","+m.getMovieResponse().getMetascore()+","+
					m.getMovieResponse().getMetascoreNumRatings()+","+CsvUtils.escape(m.getMovieResponse().getMovieRatingCategory())+"\n");
		});
	
		printWriter.write(sb.toString());
		
	}
}
