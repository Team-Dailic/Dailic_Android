package com.syaainn.dailic.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object GongBaekTheme {
    val colors: DailicColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDailicColors.current

    val typography: DailicTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalDailicTypography.current
}

@Composable
fun ProvideGongBaekColorsAndTypography(
    colors: DailicColors,
    typography: DailicTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDailicColors provides colors,
        LocalDailicTypography provides typography,
        content = content
    )
}

@Composable
fun DAILICTheme(
    content: @Composable () -> Unit
) {
    ProvideGongBaekColorsAndTypography(
        colors = defaultDailicColors,
        typography = defaultDailicTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    statusBarColor = white.toArgb()
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = true
                }
            }
        }
    }

    MaterialTheme(
        content = content
    )
}