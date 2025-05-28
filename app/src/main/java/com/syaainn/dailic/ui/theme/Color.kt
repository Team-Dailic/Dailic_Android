package com.syaainn.dailic.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val black = Color(0xFF000000)
val white = Color(0xFFFFFFFF)

// Primary
val primaryGreen1 = Color(0xFF00C911)
val primaryBlack1 = Color(0xFF1F2A37)
val primaryBeige1 = Color(0xFFF7F5F2)

// Secondary
val subGreen1 = Color(0xFF00810D)
val subGreen2 = Color(0xFFB0E742)

// Gray Scale
val gray25 = Color(0xFFFCFCFD)
val gray50 = Color(0xFFF9FAFB)
val gray100 = Color(0xFFF3F4F6)
val gray200 = Color(0xFFE5E7EB)
val gray300 = Color(0xFFD2D6DB)
val gray400 = Color(0xFF9DA4AE)
val gray500 = Color(0xFF6C737F)
val gray600 = Color(0xFF4D5761)
val gray700 = Color(0xFF384250)
val gray800 = Color(0xFF1F2A37)
val gray900 = Color(0xFF111927)
val gray950 = Color(0xFF0D121C)

@Immutable
data class DailicColors(
    val black: Color,
    val white: Color,
    val primaryGreen1: Color,
    val primaryBlack1: Color,
    val primaryBeige1: Color,
    val subGreen1: Color,
    val subGreen2: Color,
    val gray25: Color,
    val gray50: Color,
    val gray100: Color,
    val gray200: Color,
    val gray300: Color,
    val gray400: Color,
    val gray500: Color,
    val gray600: Color,
    val gray700: Color,
    val gray800: Color,
    val gray900: Color,
    val gray950: Color,
)

val defaultDailicColors = DailicColors(
    black = black,
    white = white,
    primaryGreen1 = primaryGreen1,
    primaryBlack1 = primaryBlack1,
    primaryBeige1 = primaryBeige1,
    subGreen1 = subGreen1,
    subGreen2 = subGreen2,
    gray25 = gray25,
    gray50 = gray50,
    gray100 = gray100,
    gray200 = gray200,
    gray300 = gray300,
    gray400 = gray400,
    gray500 = gray500,
    gray600 = gray600,
    gray700 = gray700,
    gray800 = gray800,
    gray900 = gray900,
    gray950 = gray950
)

val LocalDailicColors = staticCompositionLocalOf { defaultDailicColors }