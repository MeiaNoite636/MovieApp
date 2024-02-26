package com.example.themovie.presentation.main.bottombar.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.BuildConfig
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.usecase.movie.SearchMoviesUseCase
import com.example.themovie.util.Constants
import com.example.themovie.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel(){

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    private val _searchState = MutableLiveData<StateView<Unit>>()
    val searchState: LiveData<StateView<Unit>> = _searchState

    fun searchMovies(query: String?){
        viewModelScope.launch {
            try {
                _searchState.postValue(StateView.Loading())

                val searchMovies = searchMoviesUseCase(
                    apiKey = BuildConfig.API_KEY,
                    language = Constants.Movie.LANGUAGE,
                    query = query
                )

                _movieList.postValue(searchMovies)
                _searchState.postValue(StateView.Success(Unit))

            }catch (e: HttpException){
                e.printStackTrace()
                _searchState.postValue(StateView.Error(message = e.message))

            }catch (e: Exception){
                e.printStackTrace()
                _searchState.postValue(StateView.Error(message = e.message))
            }
        }
    }
}