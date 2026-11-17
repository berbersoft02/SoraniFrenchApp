package com.berbersoft.soranfrenchapp

import com.berbersoft.soranfrenchapp.data.DailyGoalManager
import org.junit.Assert.assertEquals
import org.junit.Test

class DailyGoalLogicTest {

    @Test
    fun testCalculateProgressPercentage_normal() {
        val result = DailyGoalManager.calculateProgressPercentage(progress = 5, goal = 10)
        assertEquals(0.5f, result, 0.001f)
    }

    @Test
    fun testCalculateProgressPercentage_zeroProgress() {
        val result = DailyGoalManager.calculateProgressPercentage(progress = 0, goal = 10)
        assertEquals(0f, result, 0.001f)
    }

    @Test
    fun testCalculateProgressPercentage_completed() {
        val result = DailyGoalManager.calculateProgressPercentage(progress = 10, goal = 10)
        assertEquals(1.0f, result, 0.001f)
    }

    @Test
    fun testCalculateProgressPercentage_exceedsGoalCappedAtOne() {
        val result = DailyGoalManager.calculateProgressPercentage(progress = 15, goal = 10)
        assertEquals(1.0f, result, 0.001f)
    }

    @Test
    fun testCalculateProgressPercentage_zeroGoalSafelyReturnsZero() {
        val result = DailyGoalManager.calculateProgressPercentage(progress = 5, goal = 0)
        assertEquals(0f, result, 0.001f)
    }

    @Test
    fun testCalculateProgressPercentage_negativeGoalSafelyReturnsZero() {
        val result = DailyGoalManager.calculateProgressPercentage(progress = 5, goal = -5)
        assertEquals(0f, result, 0.001f)
    }

    @Test
    fun testCalculateProgressPercentage_negativeProgressSafelyReturnsZero() {
        val result = DailyGoalManager.calculateProgressPercentage(progress = -2, goal = 10)
        assertEquals(0f, result, 0.001f)
    }
}
