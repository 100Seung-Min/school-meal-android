package com.example.data.remote.response

import com.example.domain.entity.MealEntity

data class MealResponse(
    val row: List<MealItem>
) {
    data class MealItem(
        val dishName: String,
        val mealDay: String,
        val mealTime: String,
    )

    fun MealItem.toEntity() = MealEntity.MealItem(
        dishName = dishName,
        mealDay = mealDay,
        mealTime = mealTime
    )
}

fun MealResponse.toEntity() = MealEntity(
    row = row.map { it.toEntity() }
)