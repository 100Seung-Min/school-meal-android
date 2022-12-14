package com.example.domain.usecase.school

import com.example.domain.repository.SchoolRepository
import javax.inject.Inject

class SchoolInfoUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
){
    suspend fun execute(schoolName: String) =
        schoolRepository.schoolInfo(schoolName)
}