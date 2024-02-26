package com.example.themovie.domain.local.usecase

import com.example.themovie.domain.local.repository.MovieLocalRepository
import javax.inject.Inject

class DeleteMovieUseCase @Inject constructor(
    private val movieLocalRepository: MovieLocalRepository
) {
    suspend operator fun invoke(movieId: Int?){
        return movieLocalRepository.deleteMovie(movieId)
    }
}