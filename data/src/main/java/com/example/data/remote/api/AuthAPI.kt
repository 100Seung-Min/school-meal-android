package com.example.data.remote.api

import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthAPI {
    @GET("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<Boolean>

    @GET("auth/signUp")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    )

    @GET("auth/phone")
    suspend fun sendMsg(
        @Query("phone") phone: String
    ): Response<String>
}