package com.berbersoft.soranfrenchapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// French flag inspired colors - refined with more sophisticated palette
val BlueFrance = Color(0xFF0055A4)
val BlueFranceLight = Color(0xFF3D7CC9)
val BlueFranceLighter = Color(0xFFE3F2FD)
val BlueFranceDark = Color(0xFF003B73)

val WhiteFrance = Color(0xFFF8F9FA)
val RedFrance = Color(0xFFEF4135)
val RedFranceLight = Color(0xFFF47066)
val RedFranceLighter = Color(0xFFFFEBEE)
val RedFranceDark = Color(0xFFD32F23)

// Kurdish flag inspired colors - refined
val RedKurdish = Color(0xFFE53935)
val GreenKurdish = Color(0xFF43A047)
val GreenKurdishLight = Color(0xFF76C979)
val GreenKurdishLighter = Color(0xFFE8F5E9)
val GreenKurdishDark = Color(0xFF2D7A30)
val YellowKurdish = Color(0xFFFFD54F)
val YellowKurdishLight = Color(0xFFFFE082)

// Neutral colors for backgrounds and surfaces
val Gray50 = Color(0xFFFAFAFA)
val Gray100 = Color(0xFFF5F5F5)
val Gray200 = Color(0xFFEEEEEE)
val Gray300 = Color(0xFFE0E0E0)
val Gray400 = Color(0xFFBDBDBD)
val Gray500 = Color(0xFF9E9E9E)
val Gray600 = Color(0xFF757575)
val Gray700 = Color(0xFF616161)
val Gray800 = Color(0xFF424242)
val Gray900 = Color(0xFF212121)

// Gradients
val BlueFranceGradient = Brush.verticalGradient(
    colors = listOf(BlueFrance, BlueFranceDark)
)

val RedFranceGradient = Brush.verticalGradient(
    colors = listOf(RedFrance, RedFranceDark)
)

val GreenKurdishGradient = Brush.verticalGradient(
    colors = listOf(GreenKurdish, GreenKurdishDark)
)

// Elevation colors for light theme
val ElevationLight = listOf(
    Color(0x00000000), // 0dp
    Color(0x0D000000), // 1dp
    Color(0x12000000), // 2dp
    Color(0x14000000), // 3dp
    Color(0x17000000), // 4dp
    Color(0x1A000000)  // 5dp
)

// Elevation colors for dark theme
val ElevationDark = listOf(
    Color(0x00000000), // 0dp
    Color(0x0DFFFFFF), // 1dp
    Color(0x12FFFFFF), // 2dp
    Color(0x14FFFFFF), // 3dp
    Color(0x17FFFFFF), // 4dp
    Color(0x1AFFFFFF)  // 5dp
)

// App theme colors
val PrimaryLight = BlueFrance
val SecondaryLight = RedFrance
val TertiaryLight = GreenKurdish
val BackgroundLight = Gray50
val SurfaceLight = Color.White
val ErrorLight = RedKurdish

val PrimaryDark = BlueFranceLight
val SecondaryDark = RedFranceLight
val TertiaryDark = GreenKurdishLight
val BackgroundDark = Gray900
val SurfaceDark = Gray800
val ErrorDark = RedKurdish

// Surface colors with different elevations
object SurfaceColors {
    val surfaceContainerLowest = Color(0xFFFFFFFF)
    val surfaceContainerLow = Color(0xFFF7F7F7)
    val surfaceContainer = Color(0xFFF2F2F2)
    val surfaceContainerHigh = Color(0xFFECECEC)
    val surfaceContainerHighest = Color(0xFFE6E6E6)
}
