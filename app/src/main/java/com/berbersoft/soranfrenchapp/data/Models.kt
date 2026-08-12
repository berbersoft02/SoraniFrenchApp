package com.berbersoft.soranfrenchapp.data

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.annotation.DrawableRes

data class Lesson(
    val id: String,
    val title: String,
    val description: String,
    @DrawableRes val imageResId: Int,
    val progress: Float = 0f,
    val difficulty: LessonDifficulty = LessonDifficulty.BEGINNER
)

enum class LessonDifficulty {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED
}

data class NavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

data class VocabularyItem(
    val id: String,
    val french: String,
    val english: String,
    val kurdish: String,
    val pronunciation: String,
    val example: String = "",
    val lessonId: String
) 