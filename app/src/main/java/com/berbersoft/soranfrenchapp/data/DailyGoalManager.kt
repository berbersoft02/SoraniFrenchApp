package com.berbersoft.soranfrenchapp.data

import android.content.Context
import android.content.SharedPreferences
import java.time.LocalDate

object DailyGoalManager {
    private const val PREFS_NAME = "daily_goal_prefs"
    private const val KEY_DAILY_PROGRESS = "daily_progress"
    private const val KEY_LAST_UPDATE = "last_update"
    private const val KEY_DAILY_GOAL = "daily_goal"
    private const val DEFAULT_DAILY_GOAL = 10 // Number of exercises to complete per day
    
    private lateinit var prefs: SharedPreferences
    
    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        checkAndResetProgress()
    }
    
    fun incrementProgress() {
        val currentProgress = getProgress()
        if (currentProgress < getDailyGoal()) {
            prefs.edit().putInt(KEY_DAILY_PROGRESS, currentProgress + 1).apply()
        }
    }
    
    fun getProgress(): Int {
        checkAndResetProgress()
        return prefs.getInt(KEY_DAILY_PROGRESS, 0)
    }
    
    fun getDailyGoal(): Int = prefs.getInt(KEY_DAILY_GOAL, DEFAULT_DAILY_GOAL)
    
    fun setDailyGoal(goal: Int) {
        prefs.edit().putInt(KEY_DAILY_GOAL, goal).apply()
    }
    
    fun getProgressPercentage(): Float {
        return getProgress().toFloat() / getDailyGoal().toFloat()
    }
    
    private fun checkAndResetProgress() {
        val lastUpdate = prefs.getLong(KEY_LAST_UPDATE, 0)
        val today = LocalDate.now().toEpochDay()
        
        if (lastUpdate < today) {
            // Reset progress for new day
            prefs.edit()
                .putInt(KEY_DAILY_PROGRESS, 0)
                .putLong(KEY_LAST_UPDATE, today)
                .apply()
        }
    }
}
