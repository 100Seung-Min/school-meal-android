package com.example.data.remote.response

import com.example.domain.entity.TimeEntity
import com.google.gson.annotations.SerializedName

data class TimeResponse(
    @SerializedName("time")
    val time: String,
    @SerializedName("timeName")
    val name: String
)

fun TimeResponse.toEntity() = TimeEntity(
    time = time,
    name = name
)