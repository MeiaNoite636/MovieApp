package com.example.themovie.domain.repository.movie

import com.example.themovie.data.model.CreditResponse
import com.example.themovie.data.model.MovieResponse
import com.example.themovie.data.model.MovieReviewResponse
import com.example.themovie.domain.model.MovieReview

interface MovieDetailsRepository {

    suspend fun getMovieDetails(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): MovieResponse

    suspend fun getCredits(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): CreditResponse

    suspend fun getSimilar(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): List<MovieResponse>

    suspend fun getMovieReviews(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): List<MovieReviewResponse>

}