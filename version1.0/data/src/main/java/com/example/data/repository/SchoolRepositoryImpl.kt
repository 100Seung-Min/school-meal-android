package com.example.data.repository

import com.example.data.remote.datasource.SchoolDataSource
import com.example.data.remote.response.toEntity
import com.example.domain.entity.MealEntity
import com.example.domain.entity.SchoolInfoEntity
import com.example.domain.repository.SchoolRepository
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val schoolDataSource: SchoolDataSource
): SchoolRepository {
    override suspend fun schoolInfo(schoolName: String): List<SchoolInfoEntity>? {
        return schoolDataSource.schoolInfo(schoolName)?.map { it.toEntity() }
    }

    override suspend fun schoolMeal(day: String): List<MealEntity>? {
        return schoolDataSource.schoolMeal(day)?.map { it.toEntity() }
    }
}