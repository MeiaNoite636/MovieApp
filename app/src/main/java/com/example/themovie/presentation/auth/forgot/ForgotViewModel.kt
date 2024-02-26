package com.example.themovie.presentation.auth.forgot

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.themovie.domain.usecase.auth.ForgotUseCase
import com.example.themovie.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(
    private val forgotUseCase: ForgotUseCase
) : ViewModel() {

    fun forgot(email: String): LiveData<StateView<Unit>> = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())

            val forgot = forgotUseCase.invoke(email)

            emit(StateView.Success(forgot))
        }catch (ioException: Exception){
            emit(StateView.Error(ioException.message))
        }
    }
}