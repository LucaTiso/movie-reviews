package com.luca.moviereviews.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luca.moviereviews.core.model.ReviewSearchParams;
import com.luca.moviereviews.core.requests.WebappReviewRequest;
import com.luca.moviereviews.core.service.WebappReviewService;
import com.luca.moviereviews.core.utils.EntityUtils;
import com.luca.moviereviews.jpa.entities.Movie;
import com.luca.moviereviews.jpa.entities.WebappReview;
import com.luca.moviereviews.jpa.entities.WebappUser;
import com.luca.moviereviews.jpa.repository.MovieRepository;
import com.luca.moviereviews.jpa.repository.WebappReviewRepository;
import com.luca.moviereviews.jpa.repository.WebappUserRepository;
import com.luca.moviereviews.responses.MovieResponse;
import com.luca.moviereviews.responses.MovieSearchResponse;
import com.luca.moviereviews.responses.ReviewResponse;
import com.luca.moviereviews.responses.ReviewSearchResponse;

@Service
public class WebappReviewServiceImpl implements WebappReviewService {

	@Autowired
	private WebappReviewRepository webappReviewRepository;

	@Autowired
	private WebappUserRepository webappUserReository;

	@Autowired
	private MovieRepository movieRepository;

	@Override
	@Transactional
	public void addReview(Long movieId, WebappReviewRequest request, String username) {

		// Optional<Movie> movieOptional = movieRepository.findById(movideId);

		Optional<WebappUser> optionalUser = webappUserReository.findByUsername(username);

		if (optionalUser.isPresent()) {

			WebappUser webappUser = optionalUser.get();

			WebappReview webappReview = EntityUtils.dtoToEntity(request);
			webappReview.setMovieId(movieId);
			System.out.println("movie id: " + movieId);
			webappReview.setWebappUser(webappUser);

			webappReviewRepository.save(webappReview);

		}
	}

	@Override
	@Transactional
	public void updateReview(Long movieId, Long reviewId, WebappReviewRequest request) {
		Optional<WebappReview> optionalReview = webappReviewRepository.findById(reviewId);

		if (optionalReview.isPresent()) {
			WebappReview review = optionalReview.get();
			review.setRating(request.getRating());
			review.setText(request.getText());
			review.setTitle(request.getTitle());
		}
	}

	@Override
	@Transactional
	public void deleteReview(Long movieId, Long reviewId) {

		webappReviewRepository.deleteById(reviewId);

	}

	@Override
	@Transactional(readOnly = true)
	public ReviewSearchResponse getReviews(Long movieId, ReviewSearchParams reviewSearchParams) {

		Sort sort = reviewSearchParams.getSortDirection().toUpperCase().contentEquals("DESC")
				? Sort.by(reviewSearchParams.getSortBy()).descending()
				: Sort.by(reviewSearchParams.getSortBy()).ascending();

		Specification<WebappReview> spec = (root, query, cb) -> {
			return cb.equal(root.get("movie_id"), movieId);
		};

		Page<WebappReview> reviewPage = webappReviewRepository.findPaginatedByMovieId(spec,
				PageRequest.of(reviewSearchParams.getPageNumber(), reviewSearchParams.getPageRecords(), sort));

		List<ReviewResponse> reviewList = reviewPage.getContent().stream().map(EntityUtils::entityToDto).toList();

		ReviewSearchResponse searchResponse = new ReviewSearchResponse();
		searchResponse.setReviewList(reviewList);
		searchResponse.setPageNumber(reviewPage.getNumber());
		searchResponse.setTotalNumber(reviewPage.getTotalElements());

		// if first page this should be 0
		Long numPreviousPage = reviewSearchParams.getPageNumber() * reviewSearchParams.getPageRecords() * 1l;

		searchResponse.setFromNum(numPreviousPage + 1);
		searchResponse.setToNum(numPreviousPage + reviewPage.getNumberOfElements());

		return searchResponse;
	}

}
