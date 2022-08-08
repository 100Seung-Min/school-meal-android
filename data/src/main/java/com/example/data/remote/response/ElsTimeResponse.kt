package com.example.data.remote.response

import com.example.domain.entity.ElsTimeEntity
import com.google.gson.annotations.SerializedName

data class ElsTimeResponse(
    @SerializedName("elsTimetable")
    val elsTimetable : List<Timetable?>
) {
    data class Timetable(
        @SerializedName("row")
        val row: List<TimeDateRow>?
    ) {
        data class TimeDateRow(
            @SerializedName("PERIO")
            val time: String,
            @SerializedName("ITRT_CNTNT")
            val timeName: String,
        )

        fun TimeDateRow.toEntity() = ElsTimeEntity.Timetable.TimeDateRow(
            time = time,
            timeName = timeName
        )
    }

    fun Timetable?.toEntity() = ElsTimeEntity.Timetable(
        row = this?.row?.map { it.toEntity() } ?: null
    )
}

fun ElsTimeResponse.toEntity() = ElsTimeEntity(
    elsTimetable = elsTimetable.map { it?.toEntity() }
)