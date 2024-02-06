package com.example.themovie.presenter.main.bottombar.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.themovie.BuildConfig
import com.example.themovie.data.mapper.toPresentation
import com.example.themovie.domain.usecase.movie.GetGenresUseCase
import com.example.themovie.domain.usecase.movie.GetMoviesByGenresUseCase
import com.example.themovie.util.Constants.Movie
import com.example.themovie.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getMoviesByGenresUseCase: GetMoviesByGenresUseCase
) : ViewModel(){

    fun getGenres() = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())

            val genres = getGenresUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Movie.LANGUAGE
            ).map {
                it.toPresentation()
            }

            emit(StateView.Success(genres))

        }catch (e: HttpException){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }catch (e: Exception){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

    fun getMoviesByGenres(genreId: Int?) = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())

            val movies = getMoviesByGenresUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Movie.LANGUAGE,
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
}