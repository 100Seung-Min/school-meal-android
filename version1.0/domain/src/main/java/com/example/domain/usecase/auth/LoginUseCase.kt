package com.example.domain.usecase.auth

import com.example.domain.param.LoginParam
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute(loginParam: LoginParam) =
        authRepository.login(loginParam)
}