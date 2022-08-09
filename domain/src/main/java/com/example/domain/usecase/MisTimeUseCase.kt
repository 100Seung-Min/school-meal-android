package com.example.domain.usecase

import com.example.domain.repository.TimeRepository
import javax.inject.Inject

class MisTimeUseCase @Inject constructor(
    private val timeRepository: TimeRepository
) {
    suspend fun execute(cityCode: String, className: String, schoolCode: String, endDay: String, startDay: String, grade: String) = timeRepository.getMisTime(cityCode = cityCode, className = className, schoolCode = schoolCode, endDay = endDay, startDay = startDay, grade = grade)
}