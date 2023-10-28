package com.luca.moviereviews.core.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luca.moviereviews.core.requests.WebappUserLogin;
import com.luca.moviereviews.core.requests.WebappUserRegistration;
import com.luca.moviereviews.core.requests.WebappUserRequest;
import com.luca.moviereviews.core.service.WebappUserService;
import com.luca.moviereviews.core.utils.EntityUtils;
import com.luca.moviereviews.jpa.entities.FavouritesMapping;
import com.luca.moviereviews.jpa.entities.FavouritesMappingId;
import com.luca.moviereviews.jpa.entities.Movie;
import com.luca.moviereviews.jpa.entities.WebappUser;
import com.luca.moviereviews.jpa.repository.FavouritesRepository;
import com.luca.moviereviews.jpa.repository.MovieRepository;
import com.luca.moviereviews.jpa.repository.WebappUserRepository;
import com.luca.moviereviews.responses.UserResponse;

@Service
public class WebappUserServiceImpl implements WebappUserService {

	private final WebappUserRepository webappUserRepository;

	private final MovieRepository movieRepository;

	private final FavouritesRepository favouritesRepository;

	public WebappUserServiceImpl(WebappUserRepository webappUserRepository, MovieRepository movieRepository,
			FavouritesRepository favouritesRepository) {
		this.webappUserRepository = webappUserRepository;
		this.movieRepository = movieRepository;
		this.favouritesRepository = favouritesRepository;
	}

	@Override
	@Transactional
	public void registration(WebappUserRegistration registration) {

		WebappUser user = EntityUtils.dtoToEntity(registration);
		webappUserRepository.save(user);

	}

	@Override
	@Transactional
	public UserResponse login(WebappUserLogin login) {
		UserResponse response = new UserResponse();

		response = webappUserRepository.findByUsername(login.getUsername())
				.filter(u -> u.getPassword().contentEquals(login.getPassword())).map(EntityUtils::entityToDto)
				.orElse(null);

		return response;
	}

	@Override
	@Transactional
	public UserResponse editProfile(WebappUserRequest webappUserReqest) {

		UserResponse userResponse = null;

		WebappUser user = webappUserRepository.findByUsername(webappUserReqest.getUsername()).orElse(null);
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

		WebappUser webappuser = webappUserRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("utente non trovato"));

		Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("film non trovato"));

		// webappUserRepository.addFavourite(webappuser.getId(), movie.getId());

		FavouritesMapping mapping = new FavouritesMapping(new FavouritesMappingId(webappuser.getId(), movie.getId()));
		mapping.setMovie(movie);
		mapping.setWebappUser(webappuser);

		webappUserRepository.addFavourite(webappuser, movie);

	}

	@Override
	@Transactional
	public void removeFromFavourites(Long movieId, String username) {

		WebappUser webappuser = webappUserRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("utente non trovato"));

		webappUserRepository.deleteUserFavourite(webappuser.getId(), movieId);

	}

	@Override
	@Transactional
	public void removeFromFavourites(List<Long> movieIdList, String username) {

		WebappUser webappuser = webappUserRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("utente non trovato"));

		webappUserRepository.deleteUserFavourite(webappuser.getId(), movieIdList);

	}

	@Override
	@Transactional
	public void searchFavourites(String username) {
		WebappUser webappuser = webappUserRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("utente non trovato"));

		// favouritesRepository.findBy
		List<FavouritesMapping> mappingList = favouritesRepository.findByUserId(webappuser.getId());
		
		for(FavouritesMapping m:mappingList) {
			System.out.println(m.getMovie());
		}

	}

}
