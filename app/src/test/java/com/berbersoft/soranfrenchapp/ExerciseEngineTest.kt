package com.berbersoft.soranfrenchapp

import com.berbersoft.soranfrenchapp.data.Exercise
import com.berbersoft.soranfrenchapp.data.ExerciseEngine
import com.berbersoft.soranfrenchapp.data.ExerciseResult
import com.berbersoft.soranfrenchapp.data.ExerciseType
import com.berbersoft.soranfrenchapp.data.VocabularyItem
import org.junit.Assert.*
import org.junit.Test

class ExerciseEngineTest {

    private val sampleVocab = listOf(
        VocabularyItem(
            id = "v1",
            french = "Bonjour",
            english = "Hello",
            kurdish = "سڵاو",
            pronunciation = "bohn-ZHOOR",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v2",
            french = "Merci",
            english = "Thank you",
            kurdish = "سوپاس",
            pronunciation = "mehr-SEE",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v3",
            french = "Oui",
            english = "Yes",
            kurdish = "بەڵێ",
            pronunciation = "WEE",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v4",
            french = "Non",
            english = "No",
            kurdish = "نەخێر",
            pronunciation = "NOHN",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v5",
            french = "S'il vous plaît",
            english = "Please",
            kurdish = "تکایە",
            pronunciation = "seel voo PLEH",
            lessonId = "1"
        )
    )

    @Test
    fun testGenerateOptions_containsCorrectAnswer() {
        val target = sampleVocab[0]
        val options = ExerciseEngine.generateOptions(
            correctItem = target,
            allItems = sampleVocab,
            count = 4,
            useKurdish = true
        )

        assertTrue("Options must contain correct Kurdish answer", options.contains(target.kurdish))
        assertEquals("Options size should be 4", 4, options.size)
    }

    @Test
    fun testGenerateOptions_kurdishAndEnglishModes() {
        val target = sampleVocab[1] // Merci / Thank you / سوپاس

        val kurdishOptions = ExerciseEngine.generateOptions(target, sampleVocab, count = 3, useKurdish = true)
        assertTrue(kurdishOptions.contains("سوپاس"))
        assertFalse(kurdishOptions.contains("Thank you"))

        val englishOptions = ExerciseEngine.generateOptions(target, sampleVocab, count = 3, useKurdish = false)
        assertTrue(englishOptions.contains("Thank you"))
        assertFalse(englishOptions.contains("سوپاس"))
    }

    @Test
    fun testGenerateOptions_noDuplicates() {
        val target = sampleVocab[2]
        val options = ExerciseEngine.generateOptions(target, sampleVocab, count = 4)
        assertEquals("All generated options must be distinct", options.distinct().size, options.size)
    }

    @Test
    fun testGenerateOptions_withSmallPool() {
        val smallPool = sampleVocab.take(2)
        val target = smallPool[0]
        val options = ExerciseEngine.generateOptions(target, smallPool, count = 5)

        assertEquals("Should return available options without crashing", 2, options.size)
        assertTrue(options.contains(target.kurdish))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testGenerateExercise_throwsOnEmptyVocabulary() {
        ExerciseEngine.generateExercise(emptyList())
    }

    @Test
    fun testGenerateExercise_createsValidMultipleChoiceExercise() {
        val exercise = ExerciseEngine.generateExercise(sampleVocab, type = ExerciseType.MULTIPLE_CHOICE)

        assertNotNull("Exercise ID should not be null", exercise.id)
        assertEquals(ExerciseType.MULTIPLE_CHOICE, exercise.type)
        assertTrue("Vocabulary item must belong to input pool", sampleVocab.contains(exercise.vocabularyItem))
        assertTrue("Options must contain target Kurdish answer", exercise.options.contains(exercise.vocabularyItem.kurdish))
        assertEquals(4, exercise.options.size)
    }

    @Test
    fun testGenerateExercise_translationHasEmptyOptions() {
        val exercise = ExerciseEngine.generateExercise(sampleVocab, type = ExerciseType.TRANSLATION)
        assertEquals(ExerciseType.TRANSLATION, exercise.type)
        assertTrue("Translation exercises should have empty options list", exercise.options.isEmpty())
    }

    @Test
    fun testCheckAnswer_multipleChoiceAndMatching() {
        val target = sampleVocab[0]
        val exercise = Exercise(
            id = "ex_1",
            type = ExerciseType.MULTIPLE_CHOICE,
            vocabularyItem = target,
            options = listOf("سڵاو", "سوپاس", "بەڵێ", "نەخێر")
        )

        assertTrue("Exact match should be correct", ExerciseEngine.checkAnswer(exercise, "سڵاو"))
        assertTrue("Padded whitespace should be handled", ExerciseEngine.checkAnswer(exercise, "  سڵاو  "))
        assertTrue("English match should also be accepted", ExerciseEngine.checkAnswer(exercise, "hello"))
        assertFalse("Wrong answer should return false", ExerciseEngine.checkAnswer(exercise, "سوپاس"))
        assertFalse("Null answer returns false", ExerciseEngine.checkAnswer(exercise, null))
        assertFalse("Blank answer returns false", ExerciseEngine.checkAnswer(exercise, "   "))
    }

    @Test
    fun testCheckAnswer_translation() {
        val target = sampleVocab[0] // Bonjour / Hello / سڵاو
        val exercise = Exercise(
            id = "ex_2",
            type = ExerciseType.TRANSLATION,
            vocabularyItem = target
        )

        assertTrue("French match is valid", ExerciseEngine.checkAnswer(exercise, "bonjour"))
        assertTrue("Kurdish match is valid", ExerciseEngine.checkAnswer(exercise, "سڵاو"))
        assertTrue("English match is valid", ExerciseEngine.checkAnswer(exercise, "Hello"))
        assertFalse("Mismatched word returns false", ExerciseEngine.checkAnswer(exercise, "Au revoir"))
    }

    @Test
    fun testCalculateScore() {
        assertEquals("Empty results should return 0", 0f, ExerciseEngine.calculateScore(emptyList()), 0.001f)

        val target = sampleVocab[0]
        val results = listOf(
            ExerciseResult("1", target, ExerciseType.MULTIPLE_CHOICE, isCorrect = true),
            ExerciseResult("2", target, ExerciseType.MULTIPLE_CHOICE, isCorrect = true),
            ExerciseResult("3", target, ExerciseType.TRANSLATION, isCorrect = false),
            ExerciseResult("4", target, ExerciseType.MATCHING, isCorrect = true)
        )

        // 3 out of 4 = 0.75f
        assertEquals(0.75f, ExerciseEngine.calculateScore(results), 0.001f)
    }

    @Test
    fun testCalculateAccuracy() {
        assertEquals(0f, ExerciseEngine.calculateAccuracy(0, 0), 0.001f)
        assertEquals(0f, ExerciseEngine.calculateAccuracy(5, 0), 0.001f)
        assertEquals(0f, ExerciseEngine.calculateAccuracy(-1, 10), 0.001f)
        assertEquals(0.5f, ExerciseEngine.calculateAccuracy(5, 10), 0.001f)
        assertEquals(1.0f, ExerciseEngine.calculateAccuracy(10, 10), 0.001f)
        assertEquals(1.0f, ExerciseEngine.calculateAccuracy(12, 10), 0.001f)
    }
}
