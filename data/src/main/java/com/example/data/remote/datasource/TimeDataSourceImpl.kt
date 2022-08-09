package com.example.data.remote.datasource

import com.example.data.remote.api.TimeAPI
import com.example.data.remote.response.ElsTimeResponse
import com.example.data.remote.response.HisTimeResponse
import com.example.data.remote.response.MisTimeResponse
import com.example.data.utils.BaseDataSource
import javax.inject.Inject

class TimeDataSourceImpl @Inject constructor(
    private val timeAPI: TimeAPI,
) : TimeDataSource, BaseDataSource() {
    override suspend fun getHisTime(
        cityCode: String,
        schoolCode: String,
        grade: String,
        className: String,
        startDay: String,
        endDay: String,
    ): HisTimeResponse? {
        return safeApiCall {
            timeAPI.getHisTime(cityCode = cityCode,
                schoolCode = schoolCode,
                grade = grade,
                className = className,
                startDay = startDay,
                endDay = endDay)
        }?.body()
    }

    override suspend fun getMisTime(
        cityCode: String,
        schoolCode: String,
        grade: String,
        className: String,
        startDay: String,
        endDay: String,
    ): MisTimeResponse? {
        return safeApiCall {
            timeAPI.getMisTime(cityCode = cityCode,
                schoolCode = schoolCode,
                grade = grade,
                className = className,
                startDay = startDay,
                endDay = endDay)
        }?.body()
    }

    override suspend fun getElsTime(
        cityCode: String,
        schoolCode: String,
        grade: String,
        className: String,
        startDay: String,
        endDay: String,
    ): ElsTimeResponse? {
        return safeApiCall {
            timeAPI.getElsTime(cityCode = cityCode,
                schoolCode = schoolCode,
                grade = grade,
                className = className,
                startDay = startDay,
                endDay = endDay)
        }?.body()
    }
}