package com.example.themovie.domain.usecase.movie

import com.example.themovie.data.mapper.toDomain
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    suspend operator fun invoke(apiKey: String?, language: String?, movieId: Int?): Movie{
        return movieDetailsRepository.getMovieDetails(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).toDomain()
    }
}