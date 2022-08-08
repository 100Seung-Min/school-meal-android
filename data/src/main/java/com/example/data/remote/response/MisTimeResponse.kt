package com.example.data.remote.response

import com.example.domain.entity.MisTimeEntity
import com.google.gson.annotations.SerializedName

data class MisTimeResponse(
    @SerializedName("misTimetable")
    val misTimetable : List<Timetable>
) {
    data class Timetable(
        @SerializedName("row")
        val row: List<TimeDateRow>,
    ) {
        data class TimeDateRow(
            @SerializedName("PERIO")
            val time: String,
            @SerializedName("ITRT_CNTNT")
            val timeName: String,
        )

        fun TimeDateRow.toEntity() = MisTimeEntity.Timetable.TimeDateRow(
            time = time,
            timeName = timeName
        )
    }

    fun Timetable.toEntity() = MisTimeEntity.Timetable(
        row = row.map { it.toEntity() }
    )
}

fun MisTimeResponse.toEntity() = MisTimeEntity(
    misTimetable = misTimetable.map { it.toEntity() }
)