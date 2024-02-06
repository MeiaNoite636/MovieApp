package com.example.themovie.presenter.main.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.themovie.BuildConfig
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.usecase.movie.GetCreditsUseCase
import com.example.themovie.domain.usecase.movie.GetMovieDetailsUseCase
import com.example.themovie.util.Constants
import com.example.themovie.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getCreditsUseCase: GetCreditsUseCase
) : ViewModel(){

    private val _movieId = MutableLiveData(0)
    val movieId: LiveData<Int> = _movieId

    fun getMovieDetails(movieId: Int?) = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())

            val movieDetail = getMovieDetailsUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Constants.Movie.LANGUAGE,
                movieId = movieId
            )

            emit(StateView.Success(movieDetail))

        }catch (e: HttpException){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }catch (e: Exception){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

    fun getCredits(movieId: Int?) = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())

            val result = getCreditsUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Constants.Movie.LANGUAGE,
                movieId = movieId
            )

            emit(StateView.Success(result))

        }catch (e: HttpException){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }catch (e: Exception){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

    fun setMovieId(movieId: Int){
        _movieId.value = movieId
    }
}