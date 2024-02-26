package com.example.themovie.domain.local.repository

import com.example.themovie.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalRepository {

     fun getMovies(): Flow<List<MovieEntity>>

     suspend fun insertMovie(movieEntity: MovieEntity)

     suspend fun deleteMovie(movieId: Int?)
}