package com.example.domain.param

data class SignUpParam(
    val id: String,
    val pw: String,
    val phone: String,
    val cityCode: String,
    val schoolName: String,
    val schoolCode: String,
    val `class`: String,
    val grade: String,
    val name: String
)
