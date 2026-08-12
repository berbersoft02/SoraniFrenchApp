package com.berbersoft.soranfrenchapp.data

// Exercise types for practice screen
enum class ExerciseType {
    MULTIPLE_CHOICE,
    TRANSLATION,
    MATCHING
}

// Exercise data class
data class Exercise(
    val id: String,
    val type: ExerciseType,
    val vocabularyItem: VocabularyItem,
    val options: List<String> = emptyList()
)

// Exercise result data class
data class ExerciseResult(
    val exerciseId: String,
    val vocabularyItem: VocabularyItem,
    val exerciseType: ExerciseType,
    val isCorrect: Boolean,
    val userAnswer: String? = null
)
