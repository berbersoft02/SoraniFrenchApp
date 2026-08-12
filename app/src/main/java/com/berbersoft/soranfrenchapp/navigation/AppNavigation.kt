package com.berbersoft.soranfrenchapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.berbersoft.soranfrenchapp.ui.screens.*
import com.berbersoft.soranfrenchapp.data.TextToSpeechManager

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        
        composable(Screen.Dictionary.route) {
            DictionaryScreen(
                navController = navController,
                ttsManager = TextToSpeechManager.getInstance(LocalContext.current)
            )
        }
        
        composable(Screen.VisualLearning.route) {
            VisualLearningScreen()
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        
        composable(Screen.SavedWords.route) {
            SavedWordsScreen(navController)
        }
        
        composable(
            route = Screen.LessonDetail.route,
            arguments = listOf(
                navArgument("lessonId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId") ?: "1"
            LessonDetailScreen(lessonId = lessonId, navController = navController)
        }
        
        composable(
            route = Screen.Practice.route,
            arguments = listOf(
                navArgument("lessonId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId") ?: "1"
            PracticeScreen(lessonId = lessonId, navController = navController)
        }
    }
} 