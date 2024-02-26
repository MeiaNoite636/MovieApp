package com.example.themovie.di

import com.example.themovie.data.local.repository.MovieLocalRepositoryImpl
import com.example.themovie.data.repository.auth.FirebaseAuthenticationImpl
import com.example.themovie.data.repository.movie.MovieDetailsRepositoryImpl
import com.example.themovie.data.repository.movie.MovieRepositoryImpl
import com.example.themovie.domain.local.repository.MovieLocalRepository
import com.example.themovie.domain.repository.auth.FirebaseAuthentication
import com.example.themovie.domain.repository.movie.MovieDetailsRepository
import com.example.themovie.domain.repository.movie.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsFirebaseService(
        firebaseAuthenticationImpl: FirebaseAuthenticationImpl
    ):FirebaseAuthentication

    @Binds
    abstract fun bindsMovieRepositoryImpl(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    abstract fun bindsMovieDetailsRepositoryImpl(
        movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl
    ): MovieDetailsRepository

    @Binds
    abstract fun bindsMovieLocalRepositoryImpl(
        movieLocalRepositoryImpl: MovieLocalRepositoryImpl
    ): MovieLocalRepository
}