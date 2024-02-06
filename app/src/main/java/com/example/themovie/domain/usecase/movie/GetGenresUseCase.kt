package com.example.themovie.domain.usecase.movie

import com.example.themovie.data.mapper.toDomain
import com.example.themovie.domain.model.Genre
import com.example.themovie.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(apiKey: String, language: String?): List<Genre>{
        return movieRepository.getGenres(
            apiKey = apiKey,
            language = language
        ).genres?.map { it.toDomain() } ?: emptyList()
    }
}