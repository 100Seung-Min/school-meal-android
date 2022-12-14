package com.example.data.remote.request

import com.example.domain.param.LoginParam
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val pw: String
)

fun LoginParam.toRequest() = LoginRequest(
    id = id,
    pw = pw
)