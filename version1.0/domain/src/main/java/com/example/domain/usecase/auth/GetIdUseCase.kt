package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class GetIdUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute() =
        authRepository.getId()
}