package com.example.data.remote.response

import com.example.domain.entity.MisTimeEntity
import com.google.gson.annotations.SerializedName

data class MisTimeResponse(
    @SerializedName("misTimetable")
    val misTimetable : List<TimeResponse?>
)

fun MisTimeResponse.toEntity() = MisTimeEntity(
    misTimetable = misTimetable.map { it?.toEntity() }
)