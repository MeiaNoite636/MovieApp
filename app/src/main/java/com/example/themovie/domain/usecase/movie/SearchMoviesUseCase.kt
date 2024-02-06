package com.example.themovie.domain.usecase.movie

import com.example.themovie.data.mapper.toDomain
import com.example.themovie.data.model.MovieResponse
import com.example.themovie.domain.model.Genre
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.movie.MovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(apiKey: String, language: String?, query: String?): List<Movie>{
        return movieRepository.searchMovies(
            apiKey = apiKey,
            language = language,
            query = query
        ).filter { it.backdropPath != null  }.map { it.toDomain() }
    }
}