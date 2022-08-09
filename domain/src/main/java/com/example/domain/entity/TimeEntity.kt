package com.example.domain.entity

data class TimeEntity(
    val row: List<TimeDateRow>?
) {
    data class TimeDateRow(
        val time: String,
        val timeName: String
    )
}