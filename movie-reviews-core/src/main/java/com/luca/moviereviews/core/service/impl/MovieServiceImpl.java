package com.luca.moviereviews.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luca.moviereviews.core.model.MovieSearchParams;
import com.luca.moviereviews.core.requests.MovieRequest;
import com.luca.moviereviews.core.service.MovieService;
import com.luca.moviereviews.core.utils.EntityUtils;
import com.luca.moviereviews.jpa.entities.Movie;
import com.luca.moviereviews.jpa.repository.MovieRepository;
import com.luca.moviereviews.responses.MovieResponse;
import com.luca.moviereviews.responses.MovieSearchResponse;

import jakarta.persistence.criteria.Predicate;

@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository;

	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
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
		
		
		String title="harry";
		String genre="thrillerzzz";
		Integer year=1971;
		
		
		Specification<Movie> spec = (root, query, cb) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			
		    if (title != null && !title.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }

            if (year != null) {
                predicates.add(cb.equal(root.get("year"), year));
            }

            if (genre != null && !genre.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("genre")), "%" + genre.toLowerCase() + "%"));
            }
            
           

            return cb.and(predicates.toArray(new Predicate[0]));
		};

		Page<Movie> moviePage = movieRepository
				.findAll(spec,PageRequest.of(movieSearchParams.getPageNumber()-1, movieSearchParams.getPageRecords(), sort));

		List<MovieResponse> movieList = moviePage.getContent().stream().map(EntityUtils::entityToDto).toList();

		MovieSearchResponse searchResponse = new MovieSearchResponse();
		searchResponse.setMovieList(movieList);
		searchResponse.setPageNumber(movieSearchParams.getPageNumber());
		searchResponse.setTotalNumber(moviePage.getTotalElements());

		// if first page this should be 0
		Long numPreviousPage = (movieSearchParams.getPageNumber()-1) * movieSearchParams.getPageRecords() * 1l;

		searchResponse.setFromNum(numPreviousPage + 1);
		searchResponse.setToNum(numPreviousPage + moviePage.getNumberOfElements());

		return searchResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public MovieResponse getMovie(Long id) {
		return movieRepository.findById(id).map(EntityUtils::entityToDto).orElseThrow(() -> new RuntimeException());
	}

}
