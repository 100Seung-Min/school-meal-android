package com.example.domain.entity

data class MealEntity(
    val row: List<MealItem>
) {
    data class MealItem(
        val dishName: String,
        val mealDay: String,
        val mealTime: String,
    )
}