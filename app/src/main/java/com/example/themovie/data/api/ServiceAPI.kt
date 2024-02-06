package com.example.themovie.data.api

import com.example.themovie.data.model.BasePaginationRemote
import com.example.themovie.data.model.CreditResponse
import com.example.themovie.data.model.GenresResponse
import com.example.themovie.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {

    @GET ("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
    ): GenresResponse

    @GET ("discover/movie")
    suspend fun getMoviesByGenres(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("with_genres") genreId: Int?,
    ): BasePaginationRemote<List<MovieResponse>>

    @GET ("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("query") query: String?,
    ): BasePaginationRemote<List<MovieResponse>>

    @GET ("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
    ): MovieResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
    ): CreditResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
    ): BasePaginationRemote<List<MovieResponse>>

}