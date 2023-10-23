package com.school.core.design_system.attribute

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class SchoolColor(
    val main: Color,
)

internal val LocalSchoolColors = staticCompositionLocalOf {
    SchoolColor(
        main = Color.Unspecified
    )
}

val lightColor = SchoolColor(
    main = Color(0xFFFFFFFF)
)

val darkColor = SchoolColor(
    main = Color(0xFFFFFFFF)
)