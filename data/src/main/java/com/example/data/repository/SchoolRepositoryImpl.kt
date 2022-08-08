package com.example.data.repository

import com.example.data.remote.datasource.SchoolDataSourceImpl
import com.example.data.remote.response.toEntity
import com.example.domain.entity.SchoolEntity
import com.example.domain.repository.SchoolRepository
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val schoolDataSource: SchoolDataSourceImpl
): SchoolRepository {
    override suspend fun getSchoolInfo(schoolName: String): SchoolEntity {
        return schoolDataSource.getSchoolInfo(schoolName = schoolName).toEntity()
    }
}