package com.example.themovie.domain.model

import android.os.Parcelable
import com.example.themovie.data.model.CountryResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean?,
    val backdropPath: String?,
    val genres: List<Genre>?,
    val id: Int?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Float?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Float?,
    val voteCount: Int?,
    val productionCountries: List<Country>?
) : Parcelable
