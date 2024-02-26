package com.example.themovie.presentation.main.moviegenre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.themovie.BuildConfig
import com.example.themovie.domain.usecase.movie.GetMoviesByGenresUseCase
import com.example.themovie.domain.usecase.movie.SearchMoviesUseCase
import com.example.themovie.util.Constants
import com.example.themovie.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MovieGenreViewModel @Inject constructor(
    private val getMoviesByGenresUseCase: GetMoviesByGenresUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
): ViewModel() {

    fun getMoviesByGenres(genreId: Int?) = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())

            val movies = getMoviesByGenresUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Constants.Movie.LANGUAGE,
                genreId = genreId
            )

            emit(StateView.Success(movies))

        }catch (e: HttpException){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }catch (e: Exception){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

    fun searchMovies(query: String?) = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())

            val searchMovies = searchMoviesUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Constants.Movie.LANGUAGE,
                query = query
            )

            emit(StateView.Success(searchMovies))

        }catch (e: HttpException){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }catch (e: Exception){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }
}