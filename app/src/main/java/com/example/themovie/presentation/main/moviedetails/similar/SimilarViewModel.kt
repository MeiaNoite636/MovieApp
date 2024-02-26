package com.example.themovie.presentation.main.moviedetails.similar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.themovie.BuildConfig.*
import com.example.themovie.domain.usecase.movie.GetSimilarUseCase
import com.example.themovie.util.Constants.Movie
import com.example.themovie.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SimilarViewModel @Inject constructor(
    private val getSimilarUseCase: GetSimilarUseCase
) : ViewModel(){

    fun getSimilar(movieId: Int?) = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())
            val similar = getSimilarUseCase.invoke(
                apiKey = API_KEY,
                language = Movie.LANGUAGE,
                movieId = movieId
            )

            emit(StateView.Success(similar))

        }catch (e: HttpException){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }catch (e: Exception){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }
}