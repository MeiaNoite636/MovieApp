package com.example.themovie.domain.usecase.movie

import com.example.themovie.data.mapper.toDomain
import com.example.themovie.domain.model.Genre
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetMoviesByGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(apiKey: String, language: String?, genreId: Int?): List<Movie>{
        return movieRepository.getMoviesByGenres(
            apiKey = apiKey,
            language = language,
            genreId = genreId
        ).map{
            it.toDomain()
        }
    }
}