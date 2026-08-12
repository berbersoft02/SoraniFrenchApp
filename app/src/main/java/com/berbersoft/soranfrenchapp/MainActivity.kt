package com.berbersoft.soranfrenchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.berbersoft.soranfrenchapp.navigation.AppNavigation
import com.berbersoft.soranfrenchapp.navigation.Screen
import com.berbersoft.soranfrenchapp.ui.components.BottomNavigationBar
import com.berbersoft.soranfrenchapp.ui.theme.SoranFrenchAppTheme
import com.berbersoft.soranfrenchapp.data.DailyGoalManager
import com.berbersoft.soranfrenchapp.data.TextToSpeechManager

class MainActivity : ComponentActivity() {
    private lateinit var ttsManager: TextToSpeechManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ttsManager = TextToSpeechManager.getInstance(applicationContext)
        DailyGoalManager.init(this)
        setContent {
            SoranFrenchAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    
                    // Determine if bottom navigation should be shown
                    val showBottomNav = when {
                        currentRoute == Screen.Home.route -> true
                        currentRoute == Screen.Dictionary.route -> true
                        currentRoute == Screen.Profile.route -> true
                        else -> false
                    }
                    
                    Scaffold(
                        bottomBar = {
                            if (showBottomNav) {
                                BottomNavigationBar(
                                    navController = navController,
                                    currentRoute = currentRoute
                                )
                            }
                        }
                    ) { innerPadding ->
                        AppNavigation(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
