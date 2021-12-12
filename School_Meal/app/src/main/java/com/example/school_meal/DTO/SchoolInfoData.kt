package com.example.school_meal.DTO

data class SchoolInfoData(
    val schoolInfo: ArrayList<schoolInfo>,
)

data class schoolInfo(
    val row: ArrayList<infoRow>,
)

data class infoRow(
    val SCHUL_NM: String,
    val ATPT_OFCDC_SC_CODE: String,
    val SD_SCHUL_CODE: String
)
