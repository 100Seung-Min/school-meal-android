package com.example.data.remote.response

import com.example.domain.entity.HisTimeEntity
import com.google.gson.annotations.SerializedName

data class HisTimeResponse(
    @SerializedName("hisTimetable")
    val hisTimetable : List<Timetable?>
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

        fun TimeDateRow.toEntity() = HisTimeEntity.Timetable.TimeDateRow(
            time = time,
            timeName = timeName
        )
    }

    fun Timetable?.toEntity() = HisTimeEntity.Timetable(
        row = this?.row?.map { it.toEntity() } ?: null
    )
}

fun HisTimeResponse.toEntity() = HisTimeEntity(
    hisTimetable = hisTimetable.map { it?.toEntity() }
)
