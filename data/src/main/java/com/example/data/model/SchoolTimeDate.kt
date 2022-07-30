package com.example.data.model

data class SchoolTimeDate(
    val hisTimetable : ArrayList<hisTimetable>,
    val misTimetable: ArrayList<misTimetable>,
    val elsTimetable: ArrayList<elsTimetable>
)

data class hisTimetable(
    val row: ArrayList<timeDateRow>,
)

data class misTimetable(
    val row: ArrayList<timeDateRow>
)

data class elsTimetable(
    val row: ArrayList<timeDateRow>
)

data class timeDateRow(
    val PERIO: String,
    val ITRT_CNTNT: String,
)
