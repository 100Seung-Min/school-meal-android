package com.example.domain.entity

data class MealEntity(
    val mealServiceDietInfo: List<MealServiceDietInfo>,
) {
    data class MealServiceDietInfo(
        val row: List<DietRow>
    ) {
        data class DietRow(
            val dishName: String,
            val mealDay: String,
            val mealTime: String
        )
    }
}
