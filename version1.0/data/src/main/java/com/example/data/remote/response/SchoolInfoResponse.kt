package com.example.data.remote.response

import com.example.domain.entity.SchoolInfoEntity
import com.google.gson.annotations.SerializedName

data class SchoolInfoResponse(
    @SerializedName("schoolName")
    val schoolName: String,
    @SerializedName("cityCode")
    val cityCode: String,
    @SerializedName("schoolCode")
    val schoolCode: String,
    @SerializedName("schoolClass")
    val schoolClass: String
)

fun SchoolInfoResponse.toEntity() = SchoolInfoEntity(
    schoolName = schoolName,
    cityCode = cityCode,
    schoolCode = schoolCode,
    schoolClass = schoolClass
)
