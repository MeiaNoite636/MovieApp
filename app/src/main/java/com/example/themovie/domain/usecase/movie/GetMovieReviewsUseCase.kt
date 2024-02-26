package com.example.themovie.domain.usecase.movie

import com.example.themovie.data.mapper.toDomain
import com.example.themovie.domain.model.MovieReview
import com.example.themovie.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class GetMovieReviewsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    suspend operator fun invoke(apiKey: String?, language: String?, movieId: Int?): List<MovieReview>{
        return movieDetailsRepository.getMovieReviews(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).map {
            it.toDomain()
        }
    }
}