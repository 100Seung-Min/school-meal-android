package com.example.domain.usecase

import com.example.domain.repository.SchoolRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
) {
    suspend fun execute(schoolName: String) = schoolRepository.getSchoolInfo(schoolName = schoolName)
}