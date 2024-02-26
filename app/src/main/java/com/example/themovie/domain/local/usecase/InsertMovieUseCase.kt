package com.example.themovie.domain.local.usecase

import com.example.themovie.data.local.entity.MovieEntity
import com.example.themovie.data.mapper.toEntity
import com.example.themovie.domain.local.repository.MovieLocalRepository
import com.example.themovie.domain.model.Movie
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(
    private val movieLocalRepository: MovieLocalRepository
){
    suspend operator fun invoke(movie: Movie){
        return movieLocalRepository.insertMovie(movie.toEntity())
    }
}