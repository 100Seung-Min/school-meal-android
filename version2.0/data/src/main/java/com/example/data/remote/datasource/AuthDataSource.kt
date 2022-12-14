package com.example.data.remote.datasource

import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.SignUpRequest

interface AuthDataSource {
    suspend fun login(loginRequest: LoginRequest): Boolean
    suspend fun signUp(signUpRequest: SignUpRequest)
    suspend fun sendMsg(phone: String): String
}