package com.example.data.repository

import com.example.data.remote.datasource.AuthDataSource
import com.example.data.remote.request.toRequest
import com.example.domain.param.LoginParam
import com.example.domain.param.SignUpParam
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun login(loginParam: LoginParam): Boolean {
        return authDataSource.login(loginParam.toRequest())
    }

    override suspend fun signUp(signUpParam: SignUpParam) {
        authDataSource.signUp(signUpParam.toRequest())
    }

    override suspend fun sendMsg(phone: String): String {
        return authDataSource.sendMsg(phone)
    }
}