package com.example.school_meal.DTO

data class SchoolTimeDate(
    val hisTimetable : ArrayList<hisTimetable>,
)

data class hisTimetable(
    val row: ArrayList<timeDateRow>,
)

data class timeDateRow(
    val PERIO: String,
    val ITRT_CNTNT: String,
)
