package com.berbersoft.soranfrenchapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.berbersoft.soranfrenchapp.R
import com.berbersoft.soranfrenchapp.data.DummyData
import com.berbersoft.soranfrenchapp.data.LessonDifficulty
import com.berbersoft.soranfrenchapp.navigation.Screen
import com.berbersoft.soranfrenchapp.ui.components.LessonCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonsScreen(navController: NavController) {
    var selectedDifficulty by remember { mutableStateOf<LessonDifficulty?>(null) }
    
    val filteredLessons = remember(selectedDifficulty) {
        if (selectedDifficulty == null) {
            DummyData.recentLessons
        } else {
            DummyData.recentLessons.filter { it.difficulty == selectedDifficulty }
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(text = stringResource(R.string.lessons)) 
                },
                actions = {
                    IconButton(onClick = { /* Open search */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search lessons"
                        )
                    }
                    
                    IconButton(onClick = { /* Show filter dialog */ }) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter lessons"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Difficulty filter chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedDifficulty == null,
                    onClick = { selectedDifficulty = null },
                    label = { Text("All") }
                )
                
                FilterChip(
                    selected = selectedDifficulty == LessonDifficulty.BEGINNER,
                    onClick = { selectedDifficulty = LessonDifficulty.BEGINNER },
                    label = { Text("Beginner") }
                )
                
                FilterChip(
                    selected = selectedDifficulty == LessonDifficulty.INTERMEDIATE,
                    onClick = { selectedDifficulty = LessonDifficulty.INTERMEDIATE },
                    label = { Text("Intermediate") }
                )
                
                FilterChip(
                    selected = selectedDifficulty == LessonDifficulty.ADVANCED,
                    onClick = { selectedDifficulty = LessonDifficulty.ADVANCED },
                    label = { Text("Advanced") }
                )
            }
            
            Text(
                text = stringResource(R.string.available_lessons),
                style = MaterialTheme.typography.headlineSmall
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            if (filteredLessons.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No lessons available for the selected difficulty level",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 160.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredLessons) { lesson ->
                        LessonCard(
                            lesson = lesson,
                            onClick = {
                                navController.navigate(
                                    Screen.LessonDetail.createRoute(lesson.id)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
} 