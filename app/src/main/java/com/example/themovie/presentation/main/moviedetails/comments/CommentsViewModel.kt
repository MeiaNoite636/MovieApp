package com.example.themovie.presentation.main.moviedetails.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.themovie.BuildConfig
import com.example.themovie.domain.usecase.movie.GetMovieReviewsUseCase
import com.example.themovie.util.Constants
import com.example.themovie.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase
) : ViewModel(){
    fun getMovieReviews(movieId: Int?) = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())

            val movieReviews = getMovieReviewsUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Constants.Movie.LANGUAGE_ENG,
                movieId = movieId
            )

            emit(StateView.Success(movieReviews))
        }catch (e: HttpException){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }catch (e: Exception){
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }
}