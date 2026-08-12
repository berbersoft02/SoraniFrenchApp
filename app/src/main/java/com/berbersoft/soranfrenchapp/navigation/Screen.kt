package com.berbersoft.soranfrenchapp.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Dictionary : Screen("dictionary")
    object Profile : Screen("profile")
    object SavedWords : Screen("saved_words")
    object VisualLearning : Screen("visual_learning")
    object LessonDetail : Screen("lesson/{lessonId}") {
        fun createRoute(lessonId: String) = "lesson/$lessonId"
    }
    object Practice : Screen("practice/{lessonId}") {
        fun createRoute(lessonId: String) = "practice/$lessonId"
    }
}
