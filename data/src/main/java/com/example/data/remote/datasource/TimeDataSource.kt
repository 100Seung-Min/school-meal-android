package com.example.data.remote.datasource

import com.example.data.remote.response.ElsTimeResponse
import com.example.data.remote.response.HisTimeResponse
import com.example.data.remote.response.MisTimeResponse

interface TimeDataSource {
    suspend fun getHisTime(cityCode: String, schoolCode: String, grade: String, className: String, startDay: String, endDay: String): HisTimeResponse

    suspend fun getMisTime(cityCode: String, schoolCode: String, grade: String, className: String, startDay: String, endDay: String): MisTimeResponse

    suspend fun getElsTime(cityCode: String, schoolCode: String, grade: String, className: String, startDay: String, endDay: String): ElsTimeResponse
}