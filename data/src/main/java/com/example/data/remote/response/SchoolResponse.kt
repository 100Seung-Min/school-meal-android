package com.example.data.remote.response

import com.example.domain.entity.SchoolEntity
import com.google.gson.annotations.SerializedName

data class SchoolResponse(
    @SerializedName("schoolInfo")
    val schoolInfo: List<SchoolInfo?>?
) {
    data class SchoolInfo(
        @SerializedName("row")
        val row: List<InfoRow>?
    ) {
        data class InfoRow(
            @SerializedName("SCHUL_NM")
            val schoolName: String,
            @SerializedName("ATPT_OFCDC_SC_CODE")
            val cityCode: String,
            @SerializedName("SD_SCHUL_CODE")
            val schoolCode: String,
            @SerializedName("SCHUL_KND_SC_NM")
            val schoolType: String
        )

        fun InfoRow.toEntity() = SchoolEntity.SchoolInfo.InfoRow(
            schoolName = schoolName,
            cityCode = cityCode,
            schoolCode = schoolCode,
            schoolType = schoolType
        )
    }

    fun SchoolInfo?.toEntity() = SchoolEntity.SchoolInfo(
        row = this?.row?.map { it.toEntity() } ?: null
    )
}

fun SchoolResponse.toEntity() = SchoolEntity(
    schoolInfo = schoolInfo?.map { it?.toEntity() } ?: null
)
