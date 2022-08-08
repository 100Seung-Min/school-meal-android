package com.example.data.remote.response

import com.google.gson.annotations.SerializedName

data class HisTimeResponse(
    @SerializedName("hisTimetable")
    val hisTimetable : List<Timetable>
) {
    data class Timetable(
        @SerializedName("row")
        val row: List<TimeDateRow>
    ) {
        data class TimeDateRow(
            @SerializedName("PERIO")
            val time: String,
            @SerializedName("ITRT_CNTNT")
            val timeName: String
        )
    }
}
