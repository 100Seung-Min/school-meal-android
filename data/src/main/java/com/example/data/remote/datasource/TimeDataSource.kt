package com.example.data.remote.datasource

import com.example.data.remote.response.SchoolTimeDate

interface TimeDataSource {
    suspend fun getHisTime(cityCode: String, schoolCode: String, grade: String, className: String, startDay: String, endDay: String): SchoolTimeDate

    suspend fun getMisTime(cityCode: String, schoolCode: String, grade: String, className: String, startDay: String, endDay: String): SchoolTimeDate

    suspend fun getElsTime(cityCode: String, schoolCode: String, grade: String, className: String, startDay: String, endDay: String): SchoolTimeDate
}