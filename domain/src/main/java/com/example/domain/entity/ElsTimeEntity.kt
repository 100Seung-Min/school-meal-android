package com.example.domain.entity

data class ElsTimeEntity(
    val elsTimetable : List<Timetable?>
) {
    data class Timetable(
        val row: List<TimeDateRow>?
    ) {
        data class TimeDateRow(
            val time: String,
            val timeName: String
        )
    }
}
