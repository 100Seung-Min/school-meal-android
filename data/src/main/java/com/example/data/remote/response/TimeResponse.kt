package com.example.data.remote.response

import com.example.domain.entity.TimeEntity
import com.google.gson.annotations.SerializedName

data class TimeResponse(
    @SerializedName("row")
    val row: List<TimeDateRow>?
) {
    data class TimeDateRow(
        @SerializedName("PERIO")
        val time: String,
        @SerializedName("ITRT_CNTNT")
        val timeName: String
    )

    fun TimeDateRow.toEntity() = TimeEntity.TimeDateRow(
        time = time,
        timeName = timeName
    )
}

fun TimeResponse.toEntity() = TimeEntity(
    row = row?.map { it.toEntity() } ?: null
)