package com.example.themovie.domain.repository.movie

import com.example.themovie.data.model.GenresResponse
import com.example.themovie.data.model.MovieResponse

interface MovieRepository {

    suspend fun getGenres(apiKey: String?, language: String?): GenresResponse

    suspend fun getMoviesByGenres(
        apiKey: String?,
        language: String?,
        genreId: Int?
    ): List<MovieResponse>

    suspend fun searchMovies(
        apiKey: String?,
        language: String?,
        query: String?
    ): List<MovieResponse>


}