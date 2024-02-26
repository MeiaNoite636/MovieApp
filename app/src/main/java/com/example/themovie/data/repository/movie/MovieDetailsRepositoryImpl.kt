package com.example.themovie.data.repository.movie

import com.example.themovie.data.api.ServiceAPI
import com.example.themovie.data.model.CreditResponse
import com.example.themovie.data.model.MovieResponse
import com.example.themovie.data.model.MovieReviewResponse
import com.example.themovie.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject


class MovieDetailsRepositoryImpl @Inject constructor(
    private val serviceAPI: ServiceAPI
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): MovieResponse {
        return serviceAPI.getMovieDetails(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        )
    }

    override suspend fun getCredits(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): CreditResponse {
        return serviceAPI.getCredits(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        )
    }

    override suspend fun getSimilar(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): List<MovieResponse> {
        return serviceAPI.getSimilar(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).results ?: emptyList()
    }

    override suspend fun getMovieReviews(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): List<MovieReviewResponse> {
        return serviceAPI.getMovieReviews(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).results ?: emptyList()
    }
}