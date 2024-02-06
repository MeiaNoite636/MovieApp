package com.example.themovie.presenter.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.themovie.domain.usecase.auth.RegisterUseCase
import com.example.themovie.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel(){
    fun register(email: String, password:String): LiveData<StateView<Unit>> = liveData(Dispatchers.IO){
        try {
            emit(StateView.Loading())

            val register = registerUseCase.invoke(email, password)

            emit(StateView.Success(register))
        }catch (ioException:Exception){
            emit(StateView.Error(message = ioException.message))
        }
    }
}