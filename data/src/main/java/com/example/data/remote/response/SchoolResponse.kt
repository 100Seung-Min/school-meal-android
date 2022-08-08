package com.example.data.remote.response

import com.google.gson.annotations.SerializedName

data class SchoolResponse(
    @SerializedName("schoolInfo")
    val schoolInfo: List<SchoolInfo>
) {
    data class SchoolInfo(
        @SerializedName("row")
        val row: List<InfoRow>
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
    }
}
