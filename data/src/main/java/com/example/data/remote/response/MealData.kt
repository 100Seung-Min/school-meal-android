package com.example.data.remote.response

data class SchoolMealData(
    val mealServiceDietInfo: ArrayList<mealServiceDietInfo>
)

data class mealServiceDietInfo(
    val row: ArrayList<dietRow>
)

data class dietRow(
    val DDISH_NM: String,
    val MLSV_YMD: String,
    val MMEAL_SC_NM: String
)
