package com.berbersoft.soranfrenchapp.data

import kotlin.random.Random

/**
 * Domain engine responsible for practice exercise generation, distractor selection,
 * answer evaluation, and scoring calculations.
 */
object ExerciseEngine {

    /**
     * Generates a randomized list of options (distractors + correct answer) for an exercise.
     *
     * @param correctItem The vocabulary item representing the target question.
     * @param allItems The pool of available vocabulary items to sample distractors from.
     * @param count Desired total number of options (default: 4).
     * @param useKurdish Whether to return Kurdish text (true) or English text (false).
     * @return Shuffled list of unique answer options containing the correct answer.
     */
    fun generateOptions(
        correctItem: VocabularyItem,
        allItems: List<VocabularyItem>,
        count: Int = 4,
        useKurdish: Boolean = true
    ): List<String> {
        val correctAnswer = if (useKurdish) correctItem.kurdish else correctItem.english
        val options = mutableListOf(correctAnswer)

        val distractors = allItems
            .filter { it.id != correctItem.id }
            .map { if (useKurdish) it.kurdish else it.english }
            .distinct()
            .filter { it != correctAnswer }
            .shuffled()
            .take((count - 1).coerceAtLeast(0))

        options.addAll(distractors)
        return options.shuffled()
    }

    /**
     * Generates a single exercise from a list of vocabulary items.
     *
     * @param vocabularyItems Non-empty list of available vocabulary items.
     * @param type Optional specific exercise type. If null, a type is chosen randomly.
     * @return Constructed [Exercise].
     * @throws IllegalArgumentException if [vocabularyItems] is empty.
     */
    fun generateExercise(
        vocabularyItems: List<VocabularyItem>,
        type: ExerciseType? = null
    ): Exercise {
        require(vocabularyItems.isNotEmpty()) { "Cannot generate exercise from an empty vocabulary list." }

        val randomItem = vocabularyItems.random()
        val exerciseType = type ?: ExerciseType.values().random()

        val options = when (exerciseType) {
            ExerciseType.MULTIPLE_CHOICE, ExerciseType.MATCHING -> {
                generateOptions(
                    correctItem = randomItem,
                    allItems = vocabularyItems,
                    count = 4,
                    useKurdish = true
                )
            }
            ExerciseType.TRANSLATION -> emptyList()
        }

        return Exercise(
            id = "exercise_${System.currentTimeMillis()}_${Random.nextInt(1000, 9999)}",
            type = exerciseType,
            vocabularyItem = randomItem,
            options = options
        )
    }

    /**
     * Validates a user's answer against an exercise.
     *
     * @param exercise The exercise being answered.
     * @param answer The raw user response.
     * @return True if the answer matches the expected solution, false otherwise.
     */
    fun checkAnswer(exercise: Exercise, answer: String?): Boolean {
        if (answer.isNullOrBlank()) return false
        val trimmedAnswer = answer.trim()

        return when (exercise.type) {
            ExerciseType.MULTIPLE_CHOICE,
            ExerciseType.MATCHING -> {
                trimmedAnswer.equals(exercise.vocabularyItem.kurdish.trim(), ignoreCase = true) ||
                trimmedAnswer.equals(exercise.vocabularyItem.english.trim(), ignoreCase = true)
            }
            ExerciseType.TRANSLATION -> {
                trimmedAnswer.equals(exercise.vocabularyItem.kurdish.trim(), ignoreCase = true) ||
                trimmedAnswer.equals(exercise.vocabularyItem.french.trim(), ignoreCase = true) ||
                trimmedAnswer.equals(exercise.vocabularyItem.english.trim(), ignoreCase = true)
            }
        }
    }

    /**
     * Calculates the accuracy ratio from a collection of exercise results.
     *
     * @param results List of completed exercise results.
     * @return Score between 0.0f and 1.0f. Returns 0.0f when [results] is empty.
     */
    fun calculateScore(results: List<ExerciseResult>): Float {
        if (results.isEmpty()) return 0f
        val correctCount = results.count { it.isCorrect }
        return (correctCount.toFloat() / results.size.toFloat()).coerceIn(0f, 1f)
    }

    /**
     * Calculates percentage accuracy between correct count and total count safely.
     */
    fun calculateAccuracy(correctCount: Int, totalCount: Int): Float {
        if (totalCount <= 0 || correctCount <= 0) return 0f
        return (correctCount.toFloat() / totalCount.toFloat()).coerceIn(0f, 1f)
    }
}
