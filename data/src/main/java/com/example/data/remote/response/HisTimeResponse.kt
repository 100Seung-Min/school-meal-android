package com.example.data.remote.response

import com.example.domain.entity.HisTimeEntity
import com.google.gson.annotations.SerializedName

data class HisTimeResponse(
    @SerializedName("hisTimetable")
    val hisTimetable : List<TimeResponse?>?
)

fun HisTimeResponse.toEntity() = HisTimeEntity(
    hisTimetable = hisTimetable?.map { it?.toEntity() } ?: null
)
