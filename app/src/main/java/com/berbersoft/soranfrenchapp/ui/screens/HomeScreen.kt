package com.berbersoft.soranfrenchapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.berbersoft.soranfrenchapp.R
import com.berbersoft.soranfrenchapp.data.DummyData
import com.berbersoft.soranfrenchapp.data.Lesson
import com.berbersoft.soranfrenchapp.navigation.Screen
import com.berbersoft.soranfrenchapp.ui.theme.BlueFrance
import com.berbersoft.soranfrenchapp.ui.theme.DesignSystem
import com.berbersoft.soranfrenchapp.ui.theme.RedFrance
import com.berbersoft.soranfrenchapp.ui.theme.RedFranceLight
import com.berbersoft.soranfrenchapp.ui.theme.YellowKurdish

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val lessons = remember { DummyData.recentLessons }

    Scaffold(
        // Removed bottomBar as per user request
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // App name header instead of language selection
            item {
                AppNameHeader()
            }
            // Greeting message below app name
            item {
                GreetingMessage()
            }

            // Running courses section with full width cards
            item {
                RunningCoursesSectionFullWidth(lessons, navController)
            }

            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun GreetingMessage() {
    val currentHour = remember {
        java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
    }
    val greeting = if (currentHour < 12) "Good morning" else "Good afternoon"
    val userName = "User" // Placeholder, replace with actual user name if available

    Text(
        text = "$greeting, $userName",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
    )
}

@Composable
fun AppNameHeader() {
    val gradientColors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary
    )
    Text(
        text = "SoranFrench App",
        style = MaterialTheme.typography.displayLarge.copy(
            fontWeight = FontWeight.ExtraBold,
            brush = androidx.compose.ui.graphics.Brush.linearGradient(gradientColors)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun RunningCoursesSectionFullWidth(lessons: List<com.berbersoft.soranfrenchapp.data.Lesson>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DesignSystem.Spacing.md)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Running Courses",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "See all",
                style = MaterialTheme.typography.bodyMedium.copy(color = com.berbersoft.soranfrenchapp.ui.theme.RedFrance),
                modifier = Modifier.clickable { /* TODO: Navigate to all courses */ }
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            lessons.take(3).forEach { lesson ->
                CourseCardFullWidth(lesson, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCardFullWidth(lesson: com.berbersoft.soranfrenchapp.data.Lesson, navController: NavController) {
    val vocabulary = com.berbersoft.soranfrenchapp.data.DummyData.getVocabularyForLesson(lesson.id)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable { navController.navigate(com.berbersoft.soranfrenchapp.navigation.Screen.LessonDetail.createRoute(lesson.id)) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (lesson.difficulty) {
                com.berbersoft.soranfrenchapp.data.LessonDifficulty.BEGINNER -> com.berbersoft.soranfrenchapp.ui.theme.YellowKurdish.copy(alpha = 0.3f)
                com.berbersoft.soranfrenchapp.data.LessonDifficulty.INTERMEDIATE -> com.berbersoft.soranfrenchapp.ui.theme.BlueFrance.copy(alpha = 0.3f)
                com.berbersoft.soranfrenchapp.data.LessonDifficulty.ADVANCED -> com.berbersoft.soranfrenchapp.ui.theme.RedFranceLight.copy(alpha = 0.3f)
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "${lesson.title} Language",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = lesson.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Vocabulary: ${vocabulary.size} words",
                style = MaterialTheme.typography.bodySmall,
                color = com.berbersoft.soranfrenchapp.ui.theme.BlueFrance
            )
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = lesson.progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = when (lesson.difficulty) {
                    com.berbersoft.soranfrenchapp.data.LessonDifficulty.BEGINNER -> com.berbersoft.soranfrenchapp.ui.theme.YellowKurdish
                    com.berbersoft.soranfrenchapp.data.LessonDifficulty.INTERMEDIATE -> com.berbersoft.soranfrenchapp.ui.theme.BlueFrance
                    com.berbersoft.soranfrenchapp.data.LessonDifficulty.ADVANCED -> com.berbersoft.soranfrenchapp.ui.theme.RedFrance
                },
                trackColor = Color.LightGray.copy(alpha = 0.3f)
            )
        }
    }
}

@Composable
fun LanguageSelectionSection() {
    val languages = listOf(
        Language("French", R.drawable.flag_france),
        Language("Sorani", R.drawable.flag_sorani),
        Language("English", R.drawable.flag_uk)
    )
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DesignSystem.Spacing.md)
    ) {
        Text(
            text = "Choose the language",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = DesignSystem.Spacing.sm)
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(languages) { language ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { /* TODO: Handle language selection */ }
                ) {
                    Image(
                        painter = painterResource(id = language.flagRes),
                        contentDescription = language.name,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = language.name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

data class Language(val name: String, val flagRes: Int)

@Composable
fun RunningCoursesSection(lessons: List<Lesson>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DesignSystem.Spacing.md)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Running Courses",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "See all",
                style = MaterialTheme.typography.bodyMedium.copy(color = RedFrance),
                modifier = Modifier.clickable { /* TODO: Navigate to all courses */ }
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(lessons) { lesson ->
                CourseCard(lesson, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCard(lesson: Lesson, navController: NavController) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .clickable { navController.navigate(Screen.LessonDetail.createRoute(lesson.id)) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (lesson.difficulty) {
                com.berbersoft.soranfrenchapp.data.LessonDifficulty.BEGINNER -> YellowKurdish.copy(alpha = 0.3f)
                com.berbersoft.soranfrenchapp.data.LessonDifficulty.INTERMEDIATE -> BlueFrance.copy(alpha = 0.3f)
                com.berbersoft.soranfrenchapp.data.LessonDifficulty.ADVANCED -> RedFranceLight.copy(alpha = 0.3f)
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "${lesson.title} Language",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            // Removed instructor and timeLeft as they do not exist in Lesson data class
            // Text(
            //     text = "Instructor: ${lesson.instructor ?: "Unknown"}",
            //     style = MaterialTheme.typography.bodySmall,
            //     modifier = Modifier.padding(top = 4.dp)
            // )
            // Text(
            //     text = "Time left: ${lesson.timeLeft ?: "N/A"}",
            //     style = MaterialTheme.typography.bodySmall,
            //     modifier = Modifier.padding(top = 2.dp)
            // )
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = lesson.progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = when (lesson.difficulty) {
                    com.berbersoft.soranfrenchapp.data.LessonDifficulty.BEGINNER -> YellowKurdish
                    com.berbersoft.soranfrenchapp.data.LessonDifficulty.INTERMEDIATE -> BlueFrance
                    com.berbersoft.soranfrenchapp.data.LessonDifficulty.ADVANCED -> RedFrance
                },
                trackColor = Color.LightGray.copy(alpha = 0.3f)
            )
        }
    }
}

@Composable
fun BottomNavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            icon = { Icon(Icons.Filled.List, contentDescription = "Activity") },
            label = { Text("Activity") }
        )
        NavigationBarItem(
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) },
            icon = { Icon(Icons.Filled.Star, contentDescription = "Awards") },
            label = { Text("Awards") }
        )
        NavigationBarItem(
            selected = selectedTab == 3,
            onClick = { onTabSelected(3) },
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}
