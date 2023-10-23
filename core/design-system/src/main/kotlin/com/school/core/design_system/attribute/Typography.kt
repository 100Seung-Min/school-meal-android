package com.school.core.design_system.attribute

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle

@Immutable
data class SchoolTypography(
    val default: TextStyle,
)

internal val LocalSchoolTypography = compositionLocalOf {
    SchoolTypography(
        default = TextStyle.Default
    )
}

val schoolTypography = SchoolTypography(
    default = TextStyle.Default
)