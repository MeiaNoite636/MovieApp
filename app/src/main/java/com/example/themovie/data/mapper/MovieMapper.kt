package com.example.themovie.data.mapper

import com.example.themovie.data.model.*
import com.example.themovie.domain.model.*
import com.example.themovie.presenter.model.GenrePresentation

fun GenreResponse.toDomain(): Genre{
    return Genre(
        id = id,
        name = name
    )
}

fun MovieResponse.toDomain(): Movie{
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        genres = genres?.map { it.toDomain() },
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        productionCountries = productionCountries?.map {
            it.toDomain()
        }
    )
}

fun Genre.toPresentation(): GenrePresentation{
    return GenrePresentation(
        id = id,
        name = name,
        movies = emptyList()
    )
}

fun CountryResponse.toDomain(): Country{
    return Country(
        name = name
    )
}

fun PersonResponse.toDomain():Person{
    return Person(
        adult = adult,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath,
        castId = castId,
        character = character,
        creditId = creditId,
        order = order
    )
}

fun CreditResponse.toDomain(): Credit{
    return Credit(
        cast = cast?.map {
            it.toDomain()
        }
    )
}

fun AuthorDetailsResponse.toDomain(): AuthorDetails{
    return AuthorDetails(
        name = name,
        username = username,
        avatarPath = "https://image.tmdb.org/t/p/w500$avatarPath",
        rating = rating
    )
}

fun MovieReviewResponse.toDomain(): MovieReview{
    return MovieReview(
        author = author,
        authorDetails = authorDetailsResponse?.toDomain(),
        content = content,
        createdAt = createdAt,
        id= id,
        updatedAt = updatedAt,
        url = url
    )
}