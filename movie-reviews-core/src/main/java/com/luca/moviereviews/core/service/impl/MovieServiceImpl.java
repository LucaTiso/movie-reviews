package com.luca.moviereviews.core.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

@Service
public class MovieServiceImpl implements MovieService {

	
	private MovieRepository movieRepository;
	
	
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository=movieRepository;
	}

	@Override
	@Transactional
	public void saveMovie(MovieRequest movieRequest) {

		Movie movie = EntityUtils.dtoToEntity(movieRequest);

		Integer lastPosition = movieRepository.findLastMoviePosition();

		lastPosition = lastPosition != null ? lastPosition + 1 : 1;

		movie.setCurrentPosition(lastPosition);

		movieRepository.save(movie);

	}

	@Override
	@Transactional
	public void updateMovie(MovieRequest movieRequest, Long id) {

		Optional<Movie> toUpdate = movieRepository.findById(id);

		if (toUpdate.isPresent()) {
			Movie movie = toUpdate.get();
			if (movieRequest.getCurrentPosition() != null) {
				movie.setCurrentPosition(movieRequest.getCurrentPosition());
			}
			if (movieRequest.getDuration() != null) {
				movie.setDuration(movieRequest.getDuration());
			}
			if (movieRequest.getMetascore() != null) {
				movie.setMetascore(movieRequest.getMetascore());
			}
			if (movieRequest.getMovieCast() != null) {
				movie.setMovieCast(movieRequest.getMovieCast());
			}
			if (movieRequest.getMovieCode() != null) {
				movie.setMovieCode(movieRequest.getMovieCode());
			}
			if (movieRequest.getMovieName() != null) {
				movie.setMovieName(movieRequest.getMovieName());
			}
			if (movieRequest.getMovieYear() != null) {
				movie.setMovieYear(movieRequest.getMovieYear());
			}
			if (movieRequest.getNumFavourites() != null) {
				movie.setNumFavourites(movieRequest.getNumFavourites());
			}
			if (movieRequest.getNumRatings() != null) {
				movie.setNumRatings(movieRequest.getNumRatings());
			}
			if (movieRequest.getOriginalName() != null) {
				movie.setOriginalName(movieRequest.getOriginalName());
			}
			if (movieRequest.getPlot() != null) {
				movie.setPlot(movieRequest.getPlot());
			}
			if (movieRequest.getRating() != null) {
				movie.setRating(movieRequest.getRating());
			}
			if (movieRequest.getRegia() != null) {
				movie.setRegia(movieRequest.getRegia());
			}
			if (movieRequest.getSceneggiatura() != null) {
				movie.setSceneggiatura(movieRequest.getSceneggiatura());
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

	

		Page<Movie> moviePage = movieRepository
				.findAll(PageRequest.of(movieSearchParams.getPageNumber(), movieSearchParams.getPageRecords(), sort));

		List<MovieResponse> movieList = moviePage.getContent().stream().map(EntityUtils::entityToDto).toList();

		MovieSearchResponse searchResponse = new MovieSearchResponse();
		searchResponse.setMovieList(movieList);
		searchResponse.setPageNumber(moviePage.getNumber());
		searchResponse.setTotalNumber(moviePage.getTotalElements());
		
		// if first page this should be 0
		Long numPreviousPage=movieSearchParams.getPageNumber()*movieSearchParams.getPageRecords()*1l;
		
		searchResponse.setFromNum(numPreviousPage+1);
		searchResponse.setToNum(numPreviousPage+moviePage.getNumberOfElements());
		

		return searchResponse;
	}

}
