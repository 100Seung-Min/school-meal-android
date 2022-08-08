package com.example.domain.entity

data class HisTimeEntity(
    val hisTimetable : List<Timetable>
) {
    data class Timetable(
        val row: List<TimeDateRow>
    ) {
        data class TimeDateRow(
            val time: String,
            val timeName: String
        )
    }
}