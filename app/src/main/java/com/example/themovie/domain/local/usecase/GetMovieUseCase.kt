package com.example.themovie.domain.local.usecase

import com.example.themovie.data.local.entity.MovieEntity
import com.example.themovie.data.mapper.toDomain
import com.example.themovie.domain.local.repository.MovieLocalRepository
import com.example.themovie.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val movieLocalRepository: MovieLocalRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return movieLocalRepository.getMovies().map {movieList ->
            movieList.map {
                it.toDomain()
            }
        }
    }
}