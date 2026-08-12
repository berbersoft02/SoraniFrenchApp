package com.berbersoft.soranfrenchapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Font families - using system fonts for now
// This structure makes it easy to add custom fonts later if needed
object AppFonts {
    // Primary font used for headings and UI elements
    val Primary = FontFamily.SansSerif
    
    // Secondary font used for body text
    val Secondary = FontFamily.Default
    
    // Font used specifically for language content (lessons, examples)
    val LanguageContent = FontFamily.SansSerif
}

// Set of Material typography styles optimized for language learning
val Typography = Typography(
    // Large display text for welcome screens and major headers
    displayLarge = TextStyle(
        fontFamily = AppFonts.Primary,
        fontWeight = FontWeight.Light,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    
    displayMedium = TextStyle(
        fontFamily = AppFonts.Primary,
        fontWeight = FontWeight.Light,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    
    displaySmall = TextStyle(
        fontFamily = AppFonts.Primary,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    
    // Headlines for section titles
    headlineLarge = TextStyle(
        fontFamily = AppFonts.Primary,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    
    headlineMedium = TextStyle(
        fontFamily = AppFonts.Primary,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    
    headlineSmall = TextStyle(
        fontFamily = AppFonts.Primary,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    
    // Titles for UI components like cards and dialogs
    titleLarge = TextStyle(
        fontFamily = AppFonts.Primary,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    
    titleMedium = TextStyle(
        fontFamily = AppFonts.Primary,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    
    titleSmall = TextStyle(
        fontFamily = AppFonts.Primary,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    
    // Body text - slightly larger for better readability in language content
    bodyLarge = TextStyle(
        fontFamily = AppFonts.Secondary,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    
    bodyMedium = TextStyle(
        fontFamily = AppFonts.Secondary,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    
    bodySmall = TextStyle(
        fontFamily = AppFonts.Secondary,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    
    // Labels for buttons, tags, and other UI elements
    labelLarge = TextStyle(
        fontFamily = AppFonts.Secondary,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    
    labelMedium = TextStyle(
        fontFamily = AppFonts.Secondary,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    
    labelSmall = TextStyle(
        fontFamily = AppFonts.Secondary,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

/**
 * Additional typography styles specifically for language learning content
 */
object LanguageTypography {
    // For new French vocabulary words
    val frenchVocabulary = TextStyle(
        fontFamily = AppFonts.LanguageContent,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    )
    
    // For Kurdish translations
    val kurdishTranslation = TextStyle(
        fontFamily = AppFonts.LanguageContent,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp,
    )
    
    // For lesson examples
    val example = TextStyle(
        fontFamily = AppFonts.LanguageContent,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
    )
    
    // For pronunciation guides
    val pronunciation = TextStyle(
        fontFamily = AppFonts.LanguageContent,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
    )
}