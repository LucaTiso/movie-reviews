package com.luca.moviereviews.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.service.MetacriticReviewService;
import com.luca.moviereviews.core.utils.EntityUtils;
import com.luca.moviereviews.jpa.entities.MetacriticUserReview;
import com.luca.moviereviews.jpa.entities.Movie;
import com.luca.moviereviews.jpa.entities.WebappReview;
import com.luca.moviereviews.jpa.repository.MetacriticReviewRepository;
import com.luca.moviereviews.jpa.repository.MovieRepository;
import com.luca.moviereviews.responses.MetacriticMovieReviewResponse;
import com.luca.moviereviews.responses.MetacriticReviewResponse;
import com.luca.moviereviews.responses.ReviewSearchResponse;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

@Service
public class MetacriticReviewServiceImpl implements MetacriticReviewService {

	private final MetacriticReviewRepository metacriticReviewRepository;
	
	
	private final MovieRepository movieRepository;

	public MetacriticReviewServiceImpl(MetacriticReviewRepository metacriticReviewRepository,MovieRepository movieRepository) {
		this.metacriticReviewRepository = metacriticReviewRepository;
		this.movieRepository=movieRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public MetacriticReviewResponse getReview(Long movieId, Long reviewId) {
		
	
		MetacriticUserReview r=metacriticReviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("recensione non trovata per id : " + reviewId));
		
		MetacriticReviewResponse response=EntityUtils.entityToDto(r);
		response.setTitle(r.getMovie().getTitle());
		
		return response;
		
		

	}

	@Override
	@Transactional(readOnly = true)
	public ReviewSearchResponse<MetacriticReviewResponse> getReviews(Long movieId,
			ReviewSearchParams reviewSearchParams) {
		Sort sort = reviewSearchParams.getSortDirection().toUpperCase().contentEquals("DESC")
				? Sort.by(reviewSearchParams.getSortBy()).descending()
				: Sort.by(reviewSearchParams.getSortBy()).ascending();

		System.out.println("endpoint raggiunto");

		Specification<MetacriticUserReview> spec = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (reviewSearchParams.getUsername() != null && !reviewSearchParams.getUsername().isEmpty()) {
				predicates.add(cb.like(cb.lower(root.get("username")),
						"%" + reviewSearchParams.getUsername().toLowerCase() + "%"));
			}

			if (reviewSearchParams.getText() != null && !reviewSearchParams.getText().isEmpty()) {
				predicates.add(cb.like(cb.lower(root.get("text")),
						"%" + reviewSearchParams.getUsername().toLowerCase() + "%"));
			}

			if (reviewSearchParams.getMinUserRating() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), reviewSearchParams.getMinUserRating()));
			}

			if (reviewSearchParams.getMaxUserRating() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("rating"), reviewSearchParams.getMaxUserRating()));
			}

			if (reviewSearchParams.getFromReviewDate() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("reviewDate"), reviewSearchParams.getFromReviewDate()));
			}

			if (reviewSearchParams.getToReviewDate() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("reviewDate"), reviewSearchParams.getToReviewDate()));
			}

			predicates.add(cb.equal(root.get("movieId"), movieId));

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		Page<MetacriticUserReview> reviewPage = metacriticReviewRepository.findAll(spec,
				PageRequest.of(reviewSearchParams.getPageNumber() - 1, reviewSearchParams.getPageRecords(), sort));

		List<MetacriticReviewResponse> reviewList = reviewPage.getContent().stream().map(EntityUtils::entityToDto)
				.toList();

		ReviewSearchResponse<MetacriticReviewResponse> searchResponse = new ReviewSearchResponse<>();
		searchResponse.setReviewList(reviewList);
		searchResponse.setPageNumber(reviewSearchParams.getPageNumber());
		searchResponse.setTotalNumber(reviewPage.getTotalElements());

		
		movieRepository.findById(movieId).ifPresent(m->searchResponse.setTitle(m.getTitle()));
		// if first page this should be 0
		Long numPreviousPage = (reviewSearchParams.getPageNumber() - 1) * reviewSearchParams.getPageRecords() * 1l;

		if(reviewPage.getTotalElements()>0) {
			searchResponse.setFromNum(numPreviousPage + 1);
		}else {
			searchResponse.setFromNum(0l);
		}
		searchResponse.setToNum(numPreviousPage + reviewPage.getNumberOfElements());

		return searchResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public ReviewSearchResponse<MetacriticMovieReviewResponse> getAllMovieReviews(
			ReviewSearchParams reviewSearchParams) {

		Sort sort = reviewSearchParams.getSortDirection().toUpperCase().contentEquals("DESC")
				? Sort.by(reviewSearchParams.getSortBy()).descending()
				: Sort.by(reviewSearchParams.getSortBy()).ascending();

		Specification<MetacriticUserReview> spec = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<>();
			
			
			
			if(reviewSearchParams.getTitle()!=null && !reviewSearchParams.getTitle().isEmpty()) {
				Join<WebappReview, Movie> movieJoin = root.join("movie");
				predicates.add(cb.like(cb.lower(movieJoin.get("title")),
						"%" + reviewSearchParams.getTitle().toLowerCase() + "%"));
			}

			if (reviewSearchParams.getUsername() != null && !reviewSearchParams.getUsername().isEmpty()) {
				predicates.add(cb.like(cb.lower(root.get("username")),
						"%" + reviewSearchParams.getUsername().toLowerCase() + "%"));
			}

			if (reviewSearchParams.getText() != null && !reviewSearchParams.getText().isEmpty()) {
				predicates.add(cb.like(cb.lower(root.get("text")),
						"%" + reviewSearchParams.getUsername().toLowerCase() + "%"));
			}

			if (reviewSearchParams.getMinUserRating() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), reviewSearchParams.getMinUserRating()));
			}

			if (reviewSearchParams.getMaxUserRating() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("rating"), reviewSearchParams.getMaxUserRating()));
			}

			if (reviewSearchParams.getFromReviewDate() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("reviewDate"), reviewSearchParams.getFromReviewDate()));
			}

			if (reviewSearchParams.getToReviewDate() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("reviewDate"), reviewSearchParams.getToReviewDate()));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		Page<MetacriticUserReview> reviewPage = metacriticReviewRepository.findAll(spec,
				PageRequest.of(reviewSearchParams.getPageNumber() - 1, reviewSearchParams.getPageRecords(), sort));

		List<MetacriticMovieReviewResponse> reviewList = reviewPage.getContent().stream()
				.map(EntityUtils::entityToMetacriticMovieReviewResponse).toList();

		ReviewSearchResponse<MetacriticMovieReviewResponse> searchResponse = new ReviewSearchResponse<>();
		searchResponse.setReviewList(reviewList);
		searchResponse.setPageNumber(reviewSearchParams.getPageNumber());
		searchResponse.setTotalNumber(reviewPage.getTotalElements());

		// if first page this should be 0
		Long numPreviousPage = (reviewSearchParams.getPageNumber() - 1) * reviewSearchParams.getPageRecords() * 1l;

		if(reviewPage.getTotalElements()>0) {
			searchResponse.setFromNum(numPreviousPage + 1);
		}else {
			searchResponse.setFromNum(0l);
		}
		
		searchResponse.setToNum(numPreviousPage + reviewPage.getNumberOfElements());

		return searchResponse;
	}
}
