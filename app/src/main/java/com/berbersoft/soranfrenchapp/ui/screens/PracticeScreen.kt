package com.berbersoft.soranfrenchapp.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.berbersoft.soranfrenchapp.data.*
import com.berbersoft.soranfrenchapp.ui.theme.*
import kotlin.random.Random
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun PracticeScreen(
    lessonId: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var currentExercise by remember { mutableStateOf<Exercise?>(null) }
    var exerciseResults by remember { mutableStateOf<List<ExerciseResult>>(emptyList()) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var showExplanation by remember { mutableStateOf(false) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    
    LaunchedEffect(lessonId) {
        currentExercise = generateExercise(lessonId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            
            Text(
                text = "Practice",
                style = MaterialTheme.typography.headlineMedium
            )
            
            IconButton(onClick = { /* TODO: Show progress */ }) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = "Progress"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Exercise Content
        currentExercise?.let { exercise ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                when (exercise.type) {
                    ExerciseType.MULTIPLE_CHOICE -> MultipleChoiceExercise(
                        exercise = exercise,
                        selectedAnswer = selectedAnswer,
                        onAnswerSelected = { answer -> selectedAnswer = answer },
                        showExplanation = showExplanation,
                        isCorrect = isAnswerCorrect
                    )
                    ExerciseType.TRANSLATION -> TranslationExercise(
                        exercise = exercise,
                        answer = selectedAnswer ?: "",
                        onAnswerChange = { answer -> selectedAnswer = answer },
                        showExplanation = showExplanation,
                        isCorrect = isAnswerCorrect
                    )
                    ExerciseType.MATCHING -> MatchingExercise(
                        exercise = exercise,
                        selectedAnswer = selectedAnswer,
                        onAnswerSelected = { answer -> selectedAnswer = answer },
                        showExplanation = showExplanation,
                        isCorrect = isAnswerCorrect
                    )
                }

                if (showExplanation) {
                    Spacer(modifier = Modifier.height(16.dp))
                    AnswerExplanation(vocabularyItem = exercise.vocabularyItem)
                }
            }

            // Bottom Buttons
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Button(
                    onClick = {
                        if (!showExplanation) {
                            // Check answer
                            isAnswerCorrect = checkAnswer(exercise, selectedAnswer)
                            showExplanation = true
                            
                            // Record result
                            exerciseResults = exerciseResults + ExerciseResult(
                                exerciseId = exercise.id,
                                vocabularyItem = exercise.vocabularyItem,
                                exerciseType = exercise.type,
                                isCorrect = isAnswerCorrect,
                                userAnswer = selectedAnswer
                            )
                            
                            // Update daily goal
                            DailyGoalManager.incrementProgress()
                        } else {
                            // Next exercise
                            currentExercise = generateExercise(lessonId)
                            selectedAnswer = null
                            showExplanation = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = selectedAnswer != null || showExplanation
                ) {
                    Text(
                        text = if (!showExplanation) "Check Answer" else "Next Exercise",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Composable
fun MultipleChoiceExercise(
    exercise: Exercise,
    selectedAnswer: String?,
    onAnswerSelected: (String) -> Unit,
    showExplanation: Boolean,
    isCorrect: Boolean
) {
    Column {
        Text(
            text = "Choose the correct translation:",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = exercise.vocabularyItem.french,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        exercise.options.forEach { option ->
            val isSelected = option == selectedAnswer
            val isCorrectAnswer = option == exercise.vocabularyItem.kurdish
            
            val backgroundColor = when {
                !showExplanation -> if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                isCorrectAnswer -> MaterialTheme.colorScheme.primaryContainer
                isSelected -> MaterialTheme.colorScheme.errorContainer
                else -> MaterialTheme.colorScheme.surface
            }
            
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable(enabled = !showExplanation) { onAnswerSelected(option) },
                colors = CardDefaults.cardColors(containerColor = backgroundColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Default.RadioButtonChecked,
                            contentDescription = null,
                            tint = if (showExplanation && !isCorrectAnswer) 
                                MaterialTheme.colorScheme.error 
                            else MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.RadioButtonUnchecked,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TranslationExercise(
    exercise: Exercise,
    answer: String,
    onAnswerChange: (String) -> Unit,
    showExplanation: Boolean,
    isCorrect: Boolean
) {
    Column {
        Text(
            text = "Translate to Kurdish:",
            style = MaterialTheme.typography.titleMedium
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = exercise.vocabularyItem.french,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        OutlinedTextField(
            value = answer,
            onValueChange = { if (!showExplanation) onAnswerChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Your translation") },
            enabled = !showExplanation,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = when {
                    !showExplanation -> MaterialTheme.colorScheme.primary
                    isCorrect -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.error
                },
                unfocusedBorderColor = when {
                    !showExplanation -> MaterialTheme.colorScheme.outline
                    isCorrect -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.error
                }
            )
        )
    }
}

@Composable
fun MatchingExercise(
    exercise: Exercise,
    selectedAnswer: String?,
    onAnswerSelected: (String) -> Unit,
    showExplanation: Boolean,
    isCorrect: Boolean
) {
    Column {
        Text(
            text = "Match the correct translation:",
            style = MaterialTheme.typography.titleMedium
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = exercise.vocabularyItem.french,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        exercise.options.forEach { option ->
            val isSelected = option == selectedAnswer
            val isCorrectAnswer = option == exercise.vocabularyItem.kurdish
            
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable(enabled = !showExplanation) { onAnswerSelected(option) },
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        !showExplanation -> if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                        isCorrectAnswer -> MaterialTheme.colorScheme.primaryContainer
                        isSelected -> MaterialTheme.colorScheme.errorContainer
                        else -> MaterialTheme.colorScheme.surface
                    }
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
fun AnswerExplanation(vocabularyItem: VocabularyItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Explanation",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Kurdish:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    )
                    Text(
                        text = vocabularyItem.kurdish,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Pronunciation:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    )
                    Text(
                        text = vocabularyItem.pronunciation,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            
            if (vocabularyItem.example.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Example:",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
                
                Text(
                    text = vocabularyItem.example,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

private fun generateExercise(lessonId: String): Exercise {
    val vocabularyItems = DummyData.getVocabularyForLesson(lessonId)
    val randomItem = vocabularyItems.random()
    val exerciseType = ExerciseType.values().random()
    
    val options = when (exerciseType) {
        ExerciseType.MULTIPLE_CHOICE, ExerciseType.MATCHING -> {
            val incorrectOptions = (vocabularyItems - randomItem)
                .shuffled()
                .take(3)
                .map { it.kurdish }
            (incorrectOptions + randomItem.kurdish).shuffled()
        }
        ExerciseType.TRANSLATION -> emptyList()
    }
    
    return Exercise(
        id = "exercise_${Random.nextInt()}",
        type = exerciseType,
        vocabularyItem = randomItem,
        options = options
    )
}

private fun checkAnswer(exercise: Exercise, answer: String?): Boolean {
    if (answer == null) return false
    
    return when (exercise.type) {
        ExerciseType.MULTIPLE_CHOICE,
        ExerciseType.MATCHING -> answer == exercise.vocabularyItem.kurdish
        ExerciseType.TRANSLATION -> answer.trim().equals(exercise.vocabularyItem.kurdish.trim(), ignoreCase = true)
    }
}
