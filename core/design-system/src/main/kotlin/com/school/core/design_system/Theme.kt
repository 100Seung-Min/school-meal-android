package com.school.core.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.school.core.design_system.attribute.LocalSchoolColors
import com.school.core.design_system.attribute.LocalSchoolTypography
import com.school.core.design_system.attribute.SchoolColor
import com.school.core.design_system.attribute.darkColor
import com.school.core.design_system.attribute.lightColor
import com.school.core.design_system.attribute.schoolTypography

@Composable
fun SchoolTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) darkColor else lightColor

    CompositionLocalProvider(
        LocalSchoolColors provides colors,
        LocalSchoolTypography provides schoolTypography
    ) {
        MaterialTheme(
            content = {
                Box(
                    modifier = Modifier
                        .background(SchoolTheme.colors.main)
                        .fillMaxSize()
                ) {
                    content()
                }
            }
        )
    }
}

object SchoolTheme {
    val colors: SchoolColor
        @Composable
        get() = LocalSchoolColors.current
    val typography: SchoolColor
        @Composable
        get() = LocalSchoolColors.current
}