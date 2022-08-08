package com.example.data.repository

import com.example.data.remote.datasource.TimeDataSourceImpl
import com.example.data.remote.response.toEntity
import com.example.domain.entity.ElsTimeEntity
import com.example.domain.entity.HisTimeEntity
import com.example.domain.entity.MisTimeEntity
import com.example.domain.repository.TimeRepository
import javax.inject.Inject

class TimeRepositoryImpl @Inject constructor(
    private val timeDataSource: TimeDataSourceImpl
): TimeRepository {
    override suspend fun getHisTime(
        cityCode: String,
        schoolCode: String,
        grade: String,
        className: String,
        startDay: String,
        endDay: String,
    ): HisTimeEntity {
        return timeDataSource.getHisTime(cityCode = cityCode, schoolCode = schoolCode, grade = grade, className = className, startDay = startDay, endDay = endDay).toEntity()
    }

    override suspend fun getMisTime(
        cityCode: String,
        schoolCode: String,
        grade: String,
        className: String,
        startDay: String,
        endDay: String,
    ): MisTimeEntity {
        return timeDataSource.getMisTime(cityCode = cityCode, schoolCode = schoolCode, grade = grade, className = className, startDay = startDay, endDay = endDay).toEntity()
    }

    override suspend fun getElsTime(
        cityCode: String,
        schoolCode: String,
        grade: String,
        className: String,
        startDay: String,
        endDay: String,
    ): ElsTimeEntity {
        return timeDataSource.getElsTime(cityCode = cityCode, schoolCode = schoolCode, grade = grade, className = className, startDay = startDay, endDay = endDay).toEntity()
    }
}