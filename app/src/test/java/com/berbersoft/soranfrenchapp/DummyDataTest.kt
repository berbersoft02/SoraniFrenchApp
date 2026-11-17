package com.berbersoft.soranfrenchapp

import com.berbersoft.soranfrenchapp.data.DummyData
import com.berbersoft.soranfrenchapp.data.PhraseCategory
import org.junit.Assert.*
import org.junit.Test

class DummyDataTest {

    @Test
    fun testAllVocabularyIdsAreUnique() {
        val allVocab = DummyData.getAllVocabulary()
        assertFalse("Vocabulary list should not be empty", allVocab.isEmpty())

        val ids = allVocab.map { it.id }
        val distinctIds = ids.distinct()
        assertEquals("All vocabulary IDs must be unique (no duplicates allowed)", ids.size, distinctIds.size)
    }

    @Test
    fun testSearchVocabulary_findsFrenchQuery() {
        val results = DummyData.searchVocabulary("Bonjour")
        assertFalse("Search for 'Bonjour' should yield results", results.isEmpty())
        assertTrue("All results should contain 'bonjour' in french, english, or kurdish",
            results.any { it.french.contains("Bonjour", ignoreCase = true) })
    }

    @Test
    fun testSearchVocabulary_findsEnglishQuery() {
        val results = DummyData.searchVocabulary("Hello")
        assertFalse("Search for 'Hello' should yield results", results.isEmpty())
        assertTrue("Results should match English term",
            results.any { it.english.contains("Hello", ignoreCase = true) })
    }

    @Test
    fun testSearchVocabulary_caseInsensitive() {
        val lowerResults = DummyData.searchVocabulary("merci")
        val upperResults = DummyData.searchVocabulary("MERCI")
        assertEquals("Search must be case-insensitive", lowerResults.size, upperResults.size)
    }

    @Test
    fun testSearchVocabulary_blankQueryReturnsEmpty() {
        assertTrue("Blank query should return empty list", DummyData.searchVocabulary("").isEmpty())
        assertTrue("Whitespace query should return empty list", DummyData.searchVocabulary("   ").isEmpty())
    }

    @Test
    fun testGetVocabularyForLesson_validLessons() {
        val lesson1 = DummyData.getVocabularyForLesson("1")
        val lesson2 = DummyData.getVocabularyForLesson("2")
        val lesson3 = DummyData.getVocabularyForLesson("3")

        assertFalse("Lesson 1 should have vocabulary", lesson1.isEmpty())
        assertFalse("Lesson 2 should have vocabulary", lesson2.isEmpty())
        assertFalse("Lesson 3 should have vocabulary", lesson3.isEmpty())

        assertTrue("All items in lesson 1 must have lessonId == '1'", lesson1.all { it.lessonId == "1" })
        assertTrue("All items in lesson 2 must have lessonId == '2'", lesson2.all { it.lessonId == "2" })
    }

    @Test
    fun testGetPhrasesByCategory_returnsCorrectCategory() {
        val greetingPhrases = DummyData.getPhrasesByCategory(PhraseCategory.GREETINGS)
        assertFalse("Greetings category should not be empty", greetingPhrases.isEmpty())
        assertTrue("All filtered phrases must belong to GREETINGS", greetingPhrases.all { it.category == PhraseCategory.GREETINGS })

        val emergencyPhrases = DummyData.getPhrasesByCategory(PhraseCategory.EMERGENCY)
        assertFalse("Emergency category should not be empty", emergencyPhrases.isEmpty())
        assertTrue("All filtered phrases must belong to EMERGENCY", emergencyPhrases.all { it.category == PhraseCategory.EMERGENCY })
    }

    @Test
    fun testRecentLessons_dataIntegrity() {
        val lessons = DummyData.recentLessons
        assertEquals("There should be 3 recent lessons", 3, lessons.size)

        for (lesson in lessons) {
            assertNotNull("Lesson ID must not be null", lesson.id)
            assertTrue("Lesson title must not be blank", lesson.title.isNotBlank())
            assertTrue("Lesson description must not be blank", lesson.description.isNotBlank())
            assertTrue("Lesson progress must be between 0f and 1f", lesson.progress in 0f..1f)
            assertTrue("Image resource ID must be valid", lesson.imageResId != 0)
        }
    }
}
