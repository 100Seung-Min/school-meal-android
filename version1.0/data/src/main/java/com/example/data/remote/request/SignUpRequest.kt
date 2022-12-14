package com.example.data.remote.request

import com.example.domain.param.SignUpParam
import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val pw: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("cityCode")
    val cityCode: String,
    @SerializedName("schoolName")
    val schoolName: String,
    @SerializedName("schoolCode")
    val schoolCode: String,
    @SerializedName("class")
    val `class`: String,
    @SerializedName("grade")
    val grade: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("schoolClass")
    val schoolClass: String
)

fun SignUpParam.toRequest() = SignUpRequest(
    id = id,
    pw = pw,
    phone = phone,
    cityCode = cityCode,
    schoolName = schoolName,
    schoolCode = schoolCode,
    `class` = `class`,
    grade = grade,
    name = name,
    schoolClass = schoolClass
)
