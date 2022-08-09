package com.example.data.remote.response

import com.example.domain.entity.ElsTimeEntity
import com.google.gson.annotations.SerializedName

data class ElsTimeResponse(
    @SerializedName("elsTimetable")
    val elsTimetable : List<TimeResponse?>?
)

fun ElsTimeResponse.toEntity() = ElsTimeEntity(
    elsTimetable = elsTimetable?.map { it?.toEntity() } ?: null
)