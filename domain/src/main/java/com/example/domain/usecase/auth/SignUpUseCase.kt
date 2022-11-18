package com.example.domain.usecase.auth

import com.example.domain.param.SignUpParam
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute(signUpParam: SignUpParam) =
        authRepository.signUp(signUpParam)
}