package com.example.data.remote.response

import com.example.domain.entity.SchoolInfoEntity
import com.google.gson.annotations.SerializedName

data class SchoolInfoResponse(
    @SerializedName("row")
    val row: List<SchoolInfo>
) {
    data class SchoolInfo(
        @SerializedName("schoolName")
        val schoolName: String,
        @SerializedName("cityCode")
        val cityCode: String,
        @SerializedName("schoolCode")
        val schoolCode: String,
        @SerializedName("schoolClass")
        val schoolClass: String
    )

    fun SchoolInfo.toEntity() = SchoolInfoEntity.SchoolInfo(
        schoolName = schoolName,
        cityCode = cityCode,
        schoolCode = schoolCode,
        schoolClass = schoolClass
    )
}

fun SchoolInfoResponse.toEntity() = SchoolInfoEntity(
    row = row.map { it.toEntity() }
)
