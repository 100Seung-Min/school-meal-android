package com.example.domain.usecase.school

import com.example.domain.repository.SchoolRepository
import javax.inject.Inject

class SchoolMealUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
) {
    suspend fun execute(day: String) =
        schoolRepository.schoolMeal(day)
}