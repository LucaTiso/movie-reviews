package com.luca.moviereviews.core.service.impl;

import java.io.PrintWriter;
import java.security.Principal;

import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luca.moviereviews.core.requests.ChangeEmailRequest;
import com.luca.moviereviews.core.requests.ChangePasswordRequest;
import com.luca.moviereviews.core.requests.EditProfileRequest;
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

	private final PasswordEncoder passwordEncoder;

	public WebappUserServiceImpl(SecurityUserRepository securityUserRepository, MovieRepository movieRepository,
			FavouritesRepository favouritesRepository, PasswordEncoder passwordEncoder) {
		this.securityUserRepository = securityUserRepository;
		this.movieRepository = movieRepository;
		this.favouritesRepository = favouritesRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void changePassword(ChangePasswordRequest changePasswordRequest, Principal principal) {

		UserDetails user = (UserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
			throw new IllegalStateException("Wrong password");
		}

		if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmationPassword())) {
			throw new IllegalStateException("Wrong password");
		}

		String newEncodedPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());

		SecurityUser securityUser = securityUserRepository.findByUsername(user.getUsername()).get();
		securityUser.setPassword(newEncodedPassword);

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
	@Transactional(readOnly = true)
	public List<FavouriteMappingResponse> searchFavourites(String username) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SecurityUser webappuser = securityUserRepository.findByUsername(userDetails.getUsername())
				.orElseThrow(() -> new RuntimeException("utente non trovato"));

		// favouritesRepository.findBy
		List<FavouriteMappingResponse> mappingList = favouritesRepository.findByUserId(webappuser.getId()).stream()
				.map(EntityUtils::entityToDto).toList();

		return mappingList;

	}

	@Override
	public void downloadFavourites(PrintWriter printWriter) {
		List<FavouriteMappingResponse> retrievedList = this.searchFavourites("totti");

		StringBuilder sb = new StringBuilder("");

		sb.append(
				"Position,Title,URL,Production,Star,Cast,Year,Genres,Regia,Duration,Metacritic user rating,Metacritic user num ratings,Metascore,Metascore num ratings,Movie Category\n");
		retrievedList.forEach(m -> {
			sb.append(m.getPosition() + "," + CsvUtils.escape(m.getMovieResponse().getTitle()) + ","
					+ CsvUtils.escape(m.getMovieResponse().getHref()) + ","
					+ CsvUtils.escape(m.getMovieResponse().getProduction()) + ","
					+ CsvUtils.escape(m.getMovieResponse().getStar()) + ","
					+ CsvUtils.escape(m.getMovieResponse().getCast()) + "," + m.getMovieResponse().getYear() + ","
					+ CsvUtils.escape(m.getMovieResponse().getGenre()) + ","
					+ CsvUtils.escape(m.getMovieResponse().getRegia()) + "," + m.getMovieResponse().getDuration() + ","
					+ m.getMovieResponse().getUserRating() + "," + m.getMovieResponse().getUserNumRatings() + ","
					+ m.getMovieResponse().getMetascore() + "," + m.getMovieResponse().getMetascoreNumRatings() + ","
					+ CsvUtils.escape(m.getMovieResponse().getMovieRatingCategory()) + "\n");
		});

		printWriter.write(sb.toString());

	}

	@Override
	@Transactional
	public void editProfile(EditProfileRequest editProfileRequest, Principal principal) {
		UserDetails user = (UserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		SecurityUser securityUser = securityUserRepository.findByUsername(user.getUsername()).get();

		if (editProfileRequest != null) {
			if (editProfileRequest.getCountry() != null) {
				securityUser.setCountry(editProfileRequest.getCountry());
			}
			if (editProfileRequest.getFirstName() != null) {
				securityUser.setFirstName(editProfileRequest.getFirstName());
			}
			if (editProfileRequest.getCountry() != null) {
				securityUser.setLastName(editProfileRequest.getLastName());
			}
		}

	}

	@Override
	@Transactional
	public void changeEmail(ChangeEmailRequest changeEmailRequest, Principal principal) {
		UserDetails user = (UserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		// valideremo sta mail un po meglio
		if (changeEmailRequest == null || changeEmailRequest.getEmail() == null
				|| changeEmailRequest.getEmail().length() == 0) {
			throw new IllegalStateException("Empty email");
		}

		SecurityUser securityUser = securityUserRepository.findByUsername(user.getUsername()).get();
		securityUser.setEmail(changeEmailRequest.getEmail());

	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse getProfile(Principal principal) {

		UserDetails user = (UserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

		return securityUserRepository.findByUsername(user.getUsername()).map(EntityUtils::entityToDto)
				.orElseThrow(() -> new RuntimeException("utente non trovato"));

	}
}
