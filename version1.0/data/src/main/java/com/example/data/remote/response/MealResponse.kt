package com.example.data.remote.response

import com.example.domain.entity.MealEntity

data class MealResponse(
    val dishName: String,
    val mealDay: String,
    val mealTime: String
)

fun MealResponse.toEntity() = MealEntity(
    dishName = dishName,
    mealDay = mealDay,
    mealTime = mealTime
)