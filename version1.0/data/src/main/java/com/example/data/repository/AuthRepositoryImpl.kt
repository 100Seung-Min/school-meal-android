package com.example.data.repository

import com.example.data.local.storage.LocalUserDataSource
import com.example.data.remote.datasource.AuthDataSource
import com.example.data.remote.request.toRequest
import com.example.domain.param.LoginParam
import com.example.domain.param.SignUpParam
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localUserDataSource: LocalUserDataSource
) : AuthRepository {
    override suspend fun login(loginParam: LoginParam): Boolean =
        authDataSource.login(loginParam.toRequest())

    override suspend fun signUp(signUpParam: SignUpParam) =
        authDataSource.signUp(signUpParam.toRequest())

    override suspend fun sendMsg(phone: String): String =
        authDataSource.sendMsg(phone)

    override suspend fun saveId(id: String) =
        localUserDataSource.setId(id)

    override suspend fun getId(): String =
        localUserDataSource.getId()
}