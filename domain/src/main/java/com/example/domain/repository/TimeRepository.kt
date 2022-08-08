package com.example.domain.repository

import com.example.domain.entity.ElsTimeEntity
import com.example.domain.entity.HisTimeEntity
import com.example.domain.entity.MisTimeEntity

interface TimeRepository {
    suspend fun getHisTime(cityCode: String, schoolCode: String, grade: String, className: String, startDay: String, endDay: String): HisTimeEntity

    suspend fun getMisTime(cityCode: String, schoolCode: String, grade: String, className: String, startDay: String, endDay: String): MisTimeEntity

    suspend fun getElsTime(cityCode: String, schoolCode: String, grade: String, className: String, startDay: String, endDay: String): ElsTimeEntity
}