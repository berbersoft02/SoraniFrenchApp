package com.berbersoft.soranfrenchapp.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.berbersoft.soranfrenchapp.data.DailyGoalManager
import com.berbersoft.soranfrenchapp.data.DummyData
import com.berbersoft.soranfrenchapp.data.VocabularyItem
import com.berbersoft.soranfrenchapp.data.Exercise
import com.berbersoft.soranfrenchapp.data.ExerciseType
import com.berbersoft.soranfrenchapp.ui.theme.*
import kotlin.random.Random

data class ExerciseResult(
    val exerciseId: String,
    val vocabularyItem: VocabularyItem,
    val exerciseType: ExerciseType,
    val isCorrect: Boolean,
    val userAnswer: String?
)

// Helper function to generate options for multiple choice
private fun generateOptions(
    correctItem: VocabularyItem,
    allItems: List<VocabularyItem>
): List<String> {
    val options = mutableListOf(correctItem.english)
    
    // Add 3 random incorrect options
    val incorrectOptions = allItems
        .filter { it.id != correctItem.id }
        .map { it.english }
        .shuffled()
        .take(3)
    
    options.addAll(incorrectOptions)
    return options.shuffled()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernPracticeScreen(lessonId: String, navController: NavController) {
    val vocabularyItems = remember { 
        DummyData.getVocabularyForLesson(lessonId).shuffled() 
    }
    
    val lesson = remember { 
        DummyData.recentLessons.find { it.id == lessonId } ?: DummyData.recentLessons.first() 
    }
    
    // Generate exercises
    val exercises = remember {
        val allExercises = mutableListOf<Exercise>()
        
        vocabularyItems.forEach { vocabItem ->
            val exerciseTypes = ExerciseType.values().toList().shuffled().take(Random.nextInt(1, 3))
            
            exerciseTypes.forEach { exerciseType ->
                val exercise = when (exerciseType) {
                    ExerciseType.MULTIPLE_CHOICE -> Exercise(
                        id = "mc_${vocabItem.id}",
                        type = ExerciseType.MULTIPLE_CHOICE,
                        vocabularyItem = vocabItem,
                        options = generateOptions(vocabItem, vocabularyItems)
                    )
                    ExerciseType.TRANSLATION -> Exercise(
                        id = "tr_${vocabItem.id}",
                        type = ExerciseType.TRANSLATION,
                        vocabularyItem = vocabItem
                    )
                    ExerciseType.MATCHING -> Exercise(
                        id = "ma_${vocabItem.id}",
                        type = ExerciseType.MATCHING,
                        vocabularyItem = vocabItem,
                        options = generateOptions(vocabItem, vocabularyItems)
                    )
                }
                allExercises.add(exercise)
            }
        }
        
        allExercises.shuffled().take(10)
    }
    
    var currentIndex by remember { mutableStateOf(0) }
    var showAnswer by remember { mutableStateOf(false) }
    var practiceComplete by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    
    // Track results for each exercise
    var exerciseResults by remember { mutableStateOf<List<ExerciseResult>>(emptyList()) }
    
    val currentExercise = remember(currentIndex) { 
        if (exercises.isNotEmpty() && currentIndex < exercises.size) {
            exercises[currentIndex]
        } else {
            null
        }
    }
    
    val progress = remember(currentIndex, exercises) {
        if (exercises.isEmpty()) 0f else currentIndex.toFloat() / exercises.size.toFloat()
    }
    
    val scrollState = rememberScrollState()
    
    // Fond dégradé moderne
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2980B9),
                        Color(0xFF6DD5FA),
                        Color(0xFFFFFFFF)
                    )
                )
            )
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { 
                        Text(
                            "Pratique: ${lesson.title}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ) 
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Retour",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            },
            bottomBar = {
                if (!practiceComplete && currentExercise != null) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        shadowElevation = 8.dp
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            if (showAnswer) {
                                Button(
                                    onClick = {
                                        if (currentIndex < exercises.size - 1) {
                                            currentIndex++
                                            showAnswer = false
                                            selectedOption = null
                                        } else {
                                            practiceComplete = true
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(56.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF00C9A7)
                                    ),
                                    shape = RoundedCornerShape(28.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.NavigateNext,
                                            contentDescription = null
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = if (currentIndex < exercises.size - 1) 
                                                "Question suivante" 
                                            else 
                                                "Voir les résultats",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            } else {
                                Button(
                                    onClick = {
                                        showAnswer = true
                                        
                                        // Determine if answer is correct and record result
                                        val isCorrect = when (currentExercise.type) {
                                            ExerciseType.MULTIPLE_CHOICE, ExerciseType.MATCHING -> {
                                                selectedOption == currentExercise.vocabularyItem.english
                                            }
                                            ExerciseType.TRANSLATION -> {
                                                true
                                            }
                                        }
                                        
                                        // Record the result
                                        val result = ExerciseResult(
                                            exerciseId = currentExercise.id,
                                            vocabularyItem = currentExercise.vocabularyItem,
                                            exerciseType = currentExercise.type,
                                            isCorrect = isCorrect,
                                            userAnswer = selectedOption
                                        )
                                        
                                        exerciseResults = exerciseResults + result
                                        
                                        if (isCorrect) {
                                            DailyGoalManager.incrementProgress()
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(56.dp),
                                    enabled = when (currentExercise.type) {
                                        ExerciseType.MULTIPLE_CHOICE, ExerciseType.MATCHING -> selectedOption != null
                                        ExerciseType.TRANSLATION -> true
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF2980B9),
                                        disabledContainerColor = Color(0xFF2980B9).copy(alpha = 0.5f)
                                    ),
                                    shape = RoundedCornerShape(28.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = null
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Vérifier la réponse",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (practiceComplete) {
                    // Practice complete screen with detailed results
                    PracticeCompleteScreen(
                        results = exerciseResults,
                        onRetry = {
                            currentIndex = 0
                            exerciseResults = emptyList()
                            showAnswer = false
                            selectedOption = null
                            practiceComplete = false
                        },
                        onFinish = {
                            navController.popBackStack()
                        }
                    )
                } else if (currentExercise != null) {
                    // Practice question screen
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                    ) {
                        // Progress section avec animation
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp)
                        ) {
                            LinearProgressIndicator(
                                progress = { progress },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(12.dp)
                                    .clip(RoundedCornerShape(6.dp)),
                                color = Color(0xFF00C9A7),
                                trackColor = Color.White.copy(alpha = 0.3f)
                            )
                            
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "🚀",
                                    fontSize = 24.sp
                                )
                                Text(
                                    "Question ${currentIndex + 1}/${exercises.size}",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "⭐",
                                    fontSize = 24.sp
                                )
                            }
                        }
                        
                        // Exercise card based on type - avec animation
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            when (currentExercise.type) {
                                ExerciseType.MULTIPLE_CHOICE -> MultipleChoiceExerciseModern(
                                    exercise = currentExercise,
                                    selectedOption = selectedOption,
                                    showAnswer = showAnswer,
                                    onOptionSelected = { selectedOption = it }
                                )
                                ExerciseType.TRANSLATION -> TranslationExerciseModern(
                                    exercise = currentExercise,
                                    showAnswer = showAnswer
                                )
                                ExerciseType.MATCHING -> MatchingExerciseModern(
                                    exercise = currentExercise,
                                    selectedOption = selectedOption,
                                    showAnswer = showAnswer,
                                    onOptionSelected = { selectedOption = it }
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Feedback card - conditionally visible
                        AnimatedVisibility(
                            visible = showAnswer,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            FeedbackCardModern(
                                exercise = currentExercise,
                                selectedOption = selectedOption
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PracticeCompleteScreen(results: List<ExerciseResult>, onRetry: () -> Unit, onFinish: () -> Unit) {
    val correctAnswers = results.count { it.isCorrect }
    val totalQuestions = results.size
    val scorePercentage = if (totalQuestions > 0) (correctAnswers * 100) / totalQuestions else 0
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Score Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.95f)
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (scorePercentage >= 80) "🎉 Excellent!" 
                          else if (scorePercentage >= 60) "👏 Bien joué!" 
                          else "💪 Continue!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2980B9)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "$correctAnswers/$totalQuestions",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00C9A7)
                )
                
                Text(
                    text = "réponses correctes",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                LinearProgressIndicator(
                    progress = { scorePercentage / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Color(0xFF00C9A7),
                    trackColor = Color.LightGray
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "$scorePercentage%",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2980B9)
                )
            }
        }
        
        // Results breakdown
        Text(
            text = "Détail des réponses",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2980B9),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        results.forEachIndexed { index, result ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (result.isCorrect) 
                        Color(0xFFB2F7EF) 
                    else 
                        Color(0xFFFFE0E0)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = result.vocabularyItem.french,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2980B9)
                        )
                        Text(
                            text = result.vocabularyItem.english,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                    
                    Icon(
                        imageVector = if (result.isCorrect) Icons.Default.Check else Icons.Default.Close,
                        contentDescription = null,
                        tint = if (result.isCorrect) Color(0xFF00C9A7) else Color(0xFFFF6F61),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = onRetry,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF2980B9)
                ),
                border = BorderStroke(2.dp, Color(0xFF2980B9)),
                shape = RoundedCornerShape(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Recommencer",
                    fontWeight = FontWeight.Bold
                )
            }
            
            Button(
                onClick = onFinish,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00C9A7)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Terminer",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun MultipleChoiceExerciseModern(
    exercise: Exercise,
    selectedOption: String?,
    showAnswer: Boolean,
    onOptionSelected: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.95f)
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "Quelle est la traduction de :",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF2980B9).copy(alpha = 0.7f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = exercise.vocabularyItem.french,
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF2980B9),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            exercise.options.forEachIndexed { index, option ->
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically { it * (index + 1) * 100 }
                ) {
                    val backgroundColor = animateColorAsState(
                        targetValue = when {
                            showAnswer && option == exercise.vocabularyItem.english -> Color(0xFFB2F7EF)
                            showAnswer && option == selectedOption && option != exercise.vocabularyItem.english -> Color(0xFFFFE0E0)
                            option == selectedOption -> Color(0xFFD6EFFF)
                            else -> Color.White
                        }
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable(enabled = !showAnswer) { onOptionSelected(option) },
                        colors = CardDefaults.cardColors(containerColor = backgroundColor.value),
                        border = BorderStroke(
                            width = 2.dp,
                            color = when {
                                showAnswer && option == exercise.vocabularyItem.english -> Color(0xFF00C9A7)
                                showAnswer && option == selectedOption && option != exercise.vocabularyItem.english -> Color(0xFFFF6F61)
                                option == selectedOption -> Color(0xFF2980B9)
                                else -> Color.LightGray
                            }
                        ),
                        shape = RoundedCornerShape(16.dp)
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
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF2980B9)
                            )
                            
                            if (showAnswer) {
                                Icon(
                                    imageVector = if (option == exercise.vocabularyItem.english) 
                                        Icons.Default.Check 
                                    else if (option == selectedOption) 
                                        Icons.Default.Close 
                                    else 
                                        Icons.Default.RadioButtonUnchecked,
                                    contentDescription = null,
                                    tint = if (option == exercise.vocabularyItem.english) 
                                        Color(0xFF00C9A7) 
                                    else if (option == selectedOption) 
                                        Color(0xFFFF6F61) 
                                    else 
                                        Color.Gray
                                )
                            } else if (option == selectedOption) {
                                Icon(
                                    imageVector = Icons.Default.RadioButtonChecked,
                                    contentDescription = null,
                                    tint = Color(0xFF2980B9)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.RadioButtonUnchecked,
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                            }
                        }
                    }
                }
                
                if (index < exercise.options.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun TranslationExerciseModern(
    exercise: Exercise,
    showAnswer: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.95f)
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Étudiez cette traduction :",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF2980B9).copy(alpha = 0.7f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = exercise.vocabularyItem.french,
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF2980B9),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            if (showAnswer) {
                Text(
                    text = exercise.vocabularyItem.english,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF00C9A7),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text(
                    text = "Cliquez sur 'Vérifier' pour voir la traduction",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun MatchingExerciseModern(
    exercise: Exercise,
    selectedOption: String?,
    showAnswer: Boolean,
    onOptionSelected: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.95f)
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "Associez le mot français à sa traduction :",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF2980B9).copy(alpha = 0.7f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = exercise.vocabularyItem.french,
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF2980B9),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            exercise.options.forEachIndexed { index, option ->
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically { it * (index + 1) * 100 }
                ) {
                    val backgroundColor = animateColorAsState(
                        targetValue = when {
                            showAnswer && option == exercise.vocabularyItem.english -> Color(0xFFB2F7EF)
                            showAnswer && option == selectedOption && option != exercise.vocabularyItem.english -> Color(0xFFFFE0E0)
                            option == selectedOption -> Color(0xFFD6EFFF)
                            else -> Color.White
                        }
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable(enabled = !showAnswer) { onOptionSelected(option) },
                        colors = CardDefaults.cardColors(containerColor = backgroundColor.value),
                        border = BorderStroke(
                            width = 2.dp,
                            color = when {
                                showAnswer && option == exercise.vocabularyItem.english -> Color(0xFF00C9A7)
                                showAnswer && option == selectedOption && option != exercise.vocabularyItem.english -> Color(0xFFFF6F61)
                                option == selectedOption -> Color(0xFF2980B9)
                                else -> Color.LightGray
                            }
                        ),
                        shape = RoundedCornerShape(16.dp)
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
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF2980B9)
                            )
                            
                            if (showAnswer) {
                                Icon(
                                    imageVector = if (option == exercise.vocabularyItem.english) 
                                        Icons.Default.Check 
                                    else if (option == selectedOption) 
                                        Icons.Default.Close 
                                    else 
                                        Icons.Default.RadioButtonUnchecked,
                                    contentDescription = null,
                                    tint = if (option == exercise.vocabularyItem.english) 
                                        Color(0xFF00C9A7) 
                                    else if (option == selectedOption) 
                                        Color(0xFFFF6F61) 
                                    else 
                                        Color.Gray
                                )
                            } else if (option == selectedOption) {
                                Icon(
                                    imageVector = Icons.Default.RadioButtonChecked,
                                    contentDescription = null,
                                    tint = Color(0xFF2980B9)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.RadioButtonUnchecked,
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                            }
                        }
                    }
                }
                
                if (index < exercise.options.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun FeedbackCardModern(
    exercise: Exercise,
    selectedOption: String?
) {
    val isCorrect = when (exercise.type) {
        ExerciseType.MULTIPLE_CHOICE, ExerciseType.MATCHING -> 
            selectedOption == exercise.vocabularyItem.english
        ExerciseType.TRANSLATION -> true
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCorrect) Color(0xFFB2F7EF) else Color(0xFFFFE0E0)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isCorrect) "🎉 Excellent!" else "😅 Pas tout à fait...",
                style = MaterialTheme.typography.titleLarge,
                color = if (isCorrect) Color(0xFF00C9A7) else Color(0xFFFF6F61),
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "En kurde :",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            
            Text(
                text = exercise.vocabularyItem.kurdish,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            
            Text(
                text = "Prononciation : ${exercise.vocabularyItem.pronunciation}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            
            if (exercise.vocabularyItem.example.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Exemple :",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                
                Text(
                    text = exercise.vocabularyItem.example,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
} 