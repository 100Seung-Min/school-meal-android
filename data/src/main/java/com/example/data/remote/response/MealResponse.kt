package com.example.data.remote.response

import com.example.domain.entity.MealEntity
import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("mealServiceDietInfo")
    val mealServiceDietInfo: List<MealServiceDietInfo?>,
) {
    data class MealServiceDietInfo(
        @SerializedName("row")
        val row: List<DietRow>?,
    ) {
        data class DietRow(
            @SerializedName("DDISH_NM")
            val dishName: String,
            @SerializedName("MLSV_YMD")
            val mealDay: String,
            @SerializedName("MMEAL_SC_NM")
            val mealTime: String,
        )

        fun DietRow.toEntity() = MealEntity.MealServiceDietInfo.DietRow(
            dishName = dishName,
            mealDay = mealDay,
            mealTime = mealTime
        )
    }

    fun MealServiceDietInfo?.toEntity() = MealEntity.MealServiceDietInfo(
        row = this?.row?.map { it.toEntity() } ?: null
    )
}

fun MealResponse.toEntity() = MealEntity(
    mealServiceDietInfo = mealServiceDietInfo.map { it?.toEntity() }
)
