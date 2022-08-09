package com.example.domain.usecase

import com.example.domain.repository.MealRepository
import javax.inject.Inject

class MealUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend fun execute(cityCode: String, schoolCode: String, mealType: String) = mealRepository.getMeal(cityCode = cityCode, schoolCode = schoolCode, mealType = mealType)
}