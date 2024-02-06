package com.example.themovie.data.repository.movie

import com.example.themovie.data.api.ServiceAPI
import com.example.themovie.data.model.GenresResponse
import com.example.themovie.data.model.MovieResponse
import com.example.themovie.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val serviceAPI: ServiceAPI
): MovieRepository{
    override suspend fun getGenres(apiKey: String?, language: String?): GenresResponse {
        return serviceAPI.getGenres(
            apiKey = apiKey,
            language = language
        )
    }

    override suspend fun getMoviesByGenres(
        apiKey: String?,
        language: String?,
        genreId: Int?
    ): List<MovieResponse> {
        return serviceAPI.getMoviesByGenres(
            apiKey = apiKey,
            language = language,
            genreId = genreId
        ).results ?: emptyList()
    }

    override suspend fun searchMovies(
        apiKey: String?,
        language: String?,
        query: String?
    ): List<MovieResponse> {
        return serviceAPI.searchMovies(
            apiKey = apiKey,
            language = language,
            query = query
        ).results ?: emptyList()
    }

}