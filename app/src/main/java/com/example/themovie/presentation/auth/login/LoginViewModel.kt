package com.example.themovie.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.themovie.domain.usecase.auth.LoginUseCase
import com.example.themovie.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun login(email: String, password: String): LiveData<StateView<Unit>> = liveData(Dispatchers.IO) {
            try {
                emit(StateView.Loading())

                val login = loginUseCase.invoke(email, password)

                emit(StateView.Success(login))
            } catch (ioException: Exception) {
                emit(StateView.Error(ioException.message))
            }
        }
}