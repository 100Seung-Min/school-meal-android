package com.example.data.remote.datasource

import com.example.data.remote.api.AuthAPI
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.SignUpRequest
import com.example.data.utils.BaseDataSource
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI
): AuthDataSource, BaseDataSource() {
    override suspend fun login(loginRequest: LoginRequest): Boolean {
        return safeApiCall { authAPI.login(loginRequest) }?.body() ?: false
    }

    override suspend fun signUp(signUpRequest: SignUpRequest) {
        safeApiCall { authAPI.signUp(signUpRequest) }
    }

    override suspend fun sendMsg(phone: String): String {
        return safeApiCall { authAPI.sendMsg(phone) }?.body() ?: ""
    }

}