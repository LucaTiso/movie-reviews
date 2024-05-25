package com.luca.moviereviews.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luca.moviereviews.core.model.MovieSearchParams;
import com.luca.moviereviews.core.requests.MovieRequest;
import com.luca.moviereviews.core.service.MovieService;
import com.luca.moviereviews.core.utils.EntityUtils;
import com.luca.moviereviews.jpa.entities.FavouritesMapping;
import com.luca.moviereviews.jpa.entities.Movie;
import com.luca.moviereviews.jpa.entities.MovieSearch;
import com.luca.moviereviews.jpa.entities.SecurityUser;
import com.luca.moviereviews.jpa.repository.FavouritesRepository;
import com.luca.moviereviews.jpa.repository.MovieRepository;
import com.luca.moviereviews.jpa.repository.MovieSearchRepository;
import com.luca.moviereviews.jpa.repository.SecurityUserRepository;
import com.luca.moviereviews.jpa.repository.WebappReviewRepository;
import com.luca.moviereviews.responses.MovieResponse;
import com.luca.moviereviews.responses.MovieSearchResponse;
import com.luca.moviereviews.responses.dto.MovieSearchDto;

import jakarta.persistence.criteria.Predicate;

@Service
public class MovieServiceImpl implements MovieService {

	private final MovieRepository movieRepository;

	private final SecurityUserRepository securityUserRepository;

	private final FavouritesRepository favouritesRepository;
	
	private final MovieSearchRepository movieSearchRepository;
	
	private final WebappReviewRepository webappReviewRepository;

	public MovieServiceImpl(MovieRepository movieRepository, SecurityUserRepository securityUserRepository,
			FavouritesRepository favouritesRepository,MovieSearchRepository movieSearchRepository,WebappReviewRepository webappReviewRepository) {
		this.movieRepository = movieRepository;
		this.securityUserRepository = securityUserRepository;
		this.favouritesRepository = favouritesRepository;
		this.movieSearchRepository=movieSearchRepository;
		this.webappReviewRepository=webappReviewRepository;
	}

	@Override
	@Transactional
	public void saveMovie(MovieRequest movieRequest) {

		Movie movie = EntityUtils.dtoToEntity(movieRequest);

		movieRepository.save(movie);

	}

	@Override
	@Transactional
	public void updateMovie(MovieRequest movieRequest, Long id) {

		Optional<Movie> toUpdate = movieRepository.findById(id);

		if (toUpdate.isPresent()) {
			Movie movie = toUpdate.get();

			if (movieRequest.getDuration() != null) {
				movie.setDuration(movieRequest.getDuration());
			}
			if (movieRequest.getMetascore() != null) {
				movie.setMetascore(movieRequest.getMetascore());
			}
			if (movieRequest.getMetascoreNumRatings() != null) {
				movie.setMetascoreNumRatings(movieRequest.getMetascoreNumRatings());
			}

			if (movieRequest.getGenre() != null) {
				movie.setGenre(movieRequest.getGenre());
			}

			if (movieRequest.getMovieRatingCategory() != null) {
				movie.setMovieRatingCategory(movieRequest.getMovieRatingCategory());
			}

			if (movieRequest.getProduction() != null) {
				movie.setProduction(movieRequest.getProduction());
			}

			if (movieRequest.getCast() != null) {
				movie.setMovieCast(movieRequest.getCast());
			}
			if (movieRequest.getHref() != null) {
				movie.setHref(movieRequest.getHref());
			}
			if (movieRequest.getTitle() != null) {
				movie.setTitle(movieRequest.getTitle());
			}
			if (movieRequest.getYear() != null) {
				movie.setYear(movieRequest.getYear());
			}

			if (movieRequest.getUserNumRatings() != null) {
				movie.setUserNumRatings(movieRequest.getUserNumRatings());
			}

			if (movieRequest.getPlot() != null) {
				movie.setPlot(movieRequest.getPlot());
			}
			if (movieRequest.getUserRating() != null) {
				movie.setUserRating(movieRequest.getUserRating());
			}
			if (movieRequest.getRegia() != null) {
				movie.setRegia(movieRequest.getRegia());
			}

			if (movieRequest.getStar() != null) {
				movie.setStar(movieRequest.getStar());
			}
		}

	}

	@Override
	@Transactional
	public MovieSearchResponse getMovies(MovieSearchParams movieSearchParams) {

		Sort sort = movieSearchParams.getSortDirection().toUpperCase().contentEquals("DESC")
				? Sort.by(movieSearchParams.getSortBy()).descending()
				: Sort.by(movieSearchParams.getSortBy()).ascending();
		
		
		System.out.println(movieSearchParams.getSortBy());
		System.out.println(movieSearchParams.getSortDirection());
		System.out.println(movieSearchParams.getTitle());
		System.out.println(movieSearchParams.getMaxUserRating());
		System.out.println(movieSearchParams.getMinUserRating());
		System.out.println(movieSearchParams.getPageNumber());
		System.out.println(movieSearchParams.getPageRecords());

		Specification<MovieSearch> spec = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (movieSearchParams.getTitle() != null && !movieSearchParams.getTitle().isEmpty()) {
				predicates.add(cb.like(cb.lower(root.get("title")), "%" + movieSearchParams.getTitle().toLowerCase() + "%"));
			}
			
			if(movieSearchParams.getMinUserRating()!=null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("userRating"),movieSearchParams.getMinUserRating()));
			}
			
			if(movieSearchParams.getMaxUserRating()!=null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("userRating"),movieSearchParams.getMaxUserRating()));
			}

			if (movieSearchParams.getMinMetascore() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("metascore"), movieSearchParams.getMinMetascore()));
			}

			if (movieSearchParams.getMaxMetascore() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("metascore"), movieSearchParams.getMinMetascore()));
			}
			
			if (movieSearchParams.getMinUserNumRating() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("userNumRatings"), movieSearchParams.getMinUserNumRating()));
			}

			if (movieSearchParams.getMaxUserNumRating() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("userNumRatings"), movieSearchParams.getMaxUserNumRating()));
			}
			
			if (movieSearchParams.getFromYear() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("year"), movieSearchParams.getFromYear()));
			}

			if (movieSearchParams.getToYear() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("year"), movieSearchParams.getToYear()));
			}
			
			if (movieSearchParams.getGenres() != null && movieSearchParams.getGenres().size()>0) {
				
				
				for(String genre : movieSearchParams.getGenres()) {
					predicates.add(cb.like(root.get("genre"), "%" + genre + "%"));
				}
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		
		Page<MovieSearch> moviePage = movieSearchRepository.findAll(spec,
				PageRequest.of(movieSearchParams.getPageNumber() - 1, movieSearchParams.getPageRecords(), sort));

		List<MovieSearchDto> movieList = moviePage.getContent().stream().map(EntityUtils::entityToDto).toList();

		try {

			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			
			System.out.println(userDetails.getUsername());
			SecurityUser webappuser = securityUserRepository.findByUsername(userDetails.getUsername())
					.orElseThrow(() -> new RuntimeException("utente non trovato"));

			
			System.out.println(webappuser.getUsername());
			
			List<FavouritesMapping> favourites = favouritesRepository.findByUserId(webappuser.getId());
			
			Set<Long> favouritesIds=favourites.stream().map(m->m.getMovie().getId()).collect(Collectors.toSet());
			
			System.out.println("preferiti:");
			
			favouritesIds.forEach(a->System.out.println(a));
			
			movieList.forEach(m->{
				if(favouritesIds.contains(m.getId())) {
					m.setFavourite(true);
				}
			});

		} catch (Exception e) {
			System.out.println("no user logged");
		}

		MovieSearchResponse searchResponse = new MovieSearchResponse();
		searchResponse.setMovieList(movieList);
		searchResponse.setPageNumber(movieSearchParams.getPageNumber());
		searchResponse.setTotalNumber(moviePage.getTotalElements());

		// if first page this should be 0
		Long numPreviousPage = (movieSearchParams.getPageNumber() - 1) * movieSearchParams.getPageRecords() * 1l;

		searchResponse.setFromNum(numPreviousPage + 1);
		searchResponse.setToNum(numPreviousPage + moviePage.getNumberOfElements());

		return searchResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public MovieResponse getMovie(Long id) {
		
		MovieResponse movieResponse=movieRepository.findById(id).map(EntityUtils::entityToDto).orElseThrow(() -> new RuntimeException());
		
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			/*SecurityUser webappuser = securityUserRepository.findByUsername(userDetails.getUsername())
					.orElseThrow(() -> new RuntimeException("utente non trovato"));*/
			
			
			webappReviewRepository.findByMovieAndUsername(id,userDetails.getUsername()).ifPresent(r->movieResponse.setReviewId(r.getId()));
			
		}catch(Exception e ) {
			System.out.println("no user logged");
			e.printStackTrace();
		}
		
		
		return movieResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public MovieSearchResponse getRecentMovies() {
		
		Sort sort = Sort.by("metascore").descending().and(Sort.by("metascoreNumRatings").descending());
	
		
		Specification<MovieSearch> spec = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<>();

			predicates.add(cb.isNotNull(root.get("metascore")));
			

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		
		Page<MovieSearch> moviePage = movieSearchRepository.findAll(spec,
				PageRequest.of(0, 20, sort));
		
		
		List<MovieSearchDto> movieList = moviePage.getContent().stream().map(EntityUtils::entityToDto).toList();
		
		try {

			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
			
			System.out.println(userDetails.getUsername());
			SecurityUser webappuser = securityUserRepository.findByUsername(userDetails.getUsername())
					.orElseThrow(() -> new RuntimeException("utente non trovato"));

			
			System.out.println(webappuser.getUsername());
			
			List<FavouritesMapping> favourites = favouritesRepository.findByUserId(webappuser.getId());
			
			Set<Long> favouritesIds=favourites.stream().map(m->m.getMovie().getId()).collect(Collectors.toSet());
			
			System.out.println("preferiti:");
			
			favouritesIds.forEach(a->System.out.println(a));
			
			movieList.forEach(m->{
				if(favouritesIds.contains(m.getId())) {
					m.setFavourite(true);
				}
			});

		} catch (Exception e) {
			System.out.println("no user logged");
		}
		
		
		MovieSearchResponse searchResponse = new MovieSearchResponse();
		searchResponse.setMovieList(movieList);
		searchResponse.setPageNumber(1);
		searchResponse.setTotalNumber(moviePage.getTotalElements());

		// if first page this should be 0
		

		searchResponse.setFromNum(1l);
		searchResponse.setToNum(20l);
		
		
		return searchResponse;
	}
	
	

}
