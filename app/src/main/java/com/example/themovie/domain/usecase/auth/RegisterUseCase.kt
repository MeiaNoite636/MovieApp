package com.example.themovie.domain.usecase.auth

import com.example.themovie.domain.repository.auth.FirebaseAuthentication
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val firebaseAuthentication: FirebaseAuthentication
) {
    suspend operator fun invoke(email: String, password: String) {
        return firebaseAuthentication.register(email, password)
    }
}