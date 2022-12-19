package com.example.domain.repository

import com.example.domain.param.LoginParam
import com.example.domain.param.SignUpParam

interface AuthRepository {
    suspend fun login(loginParam: LoginParam): Boolean
    suspend fun logout()
    suspend fun signUp(signUpParam: SignUpParam)
    suspend fun sendMsg(phone: String): String
    suspend fun saveId(id: String)
    suspend fun getId(): String
}