package com.berbersoft.soranfrenchapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.berbersoft.soranfrenchapp.R
import com.berbersoft.soranfrenchapp.data.DummyData
import com.berbersoft.soranfrenchapp.data.Lesson
import com.berbersoft.soranfrenchapp.data.LessonDifficulty
import com.berbersoft.soranfrenchapp.data.VocabularyItem
import com.berbersoft.soranfrenchapp.navigation.Screen
import com.berbersoft.soranfrenchapp.ui.components.GradientButton
import com.berbersoft.soranfrenchapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(lessonId: String, navController: NavController) {
    val lesson = remember { DummyData.recentLessons.find { it.id == lessonId } ?: DummyData.recentLessons.first() }
    val vocabularyItems = remember { DummyData.getVocabularyForLesson(lessonId) }
    
    var showVocabulary by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Column {
                        Text(text = lesson.title)
                        Text(
                            text = "${vocabularyItems.size} words",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(DesignSystem.Spacing.md)
        ) {
            // Lesson header with parallax effect
            item {
                ParallaxHeaderCard(lesson = lesson)
            }
            
            // Lesson description
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = lesson.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Progress indicator
                    LinearProgressIndicator(
                        progress = { lesson.progress },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = "${(lesson.progress * 100).toInt()}% Complete",
                        style = MaterialTheme.typography.labelMedium,
                        color = BlueFrance
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Practice button
                    GradientButton(
                        text = "Start Practice",
                        onClick = {
                            navController.navigate(Screen.Practice.createRoute(lessonId))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        icon = Icons.Default.PlayArrow
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Vocabulary section header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Vocabulary (${vocabularyItems.size} words)",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        
                        TextButton(
                            onClick = { showVocabulary = !showVocabulary }
                        ) {
                            Text(
                                text = if (showVocabulary) "Hide" else "Show All",
                                color = BlueFrance
                            )
                        }
                    }
                }
            }
            
            // Vocabulary list
            if (showVocabulary) {
                items(vocabularyItems) { item ->
                    VocabularyCard(vocabularyItem = item)
                }
            } else {
                // Show just a few items as preview
                itemsIndexed(vocabularyItems.take(3)) { index, item ->
                    VocabularyCard(vocabularyItem = item)
                    
                    if (index == 2 && vocabularyItems.size > 3) {
                        TextButton(
                            onClick = { showVocabulary = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "Show ${vocabularyItems.size - 3} more words",
                                color = BlueFrance
                            )
                        }
                    }
                }
            }
            
            // Additional resources section
            item {
                SectionHeader(
                    title = "Additional Resources",
                    subtitle = "Enhance your learning"
                )
                
                var showComingSoonDialog by remember { mutableStateOf<String?>(null) }
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = DesignSystem.Spacing.md),
                    horizontalArrangement = Arrangement.spacedBy(DesignSystem.Spacing.md)
                ) {
                    ResourceCard(
                        title = "Grammar Notes",
                        icon = Icons.Default.Description,
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                        onClick = { showComingSoonDialog = "Grammar Notes" },
                        modifier = Modifier.weight(1f)
                    )
                    
                    ResourceCard(
                        title = "Pronunciation Guide",
                        icon = Icons.Default.RecordVoiceOver,
                        backgroundColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f),
                        onClick = { showComingSoonDialog = "Pronunciation Guide" },
                        modifier = Modifier.weight(1f)
                    )
                }
                
                // Show dialog if showComingSoonDialog is not null
                showComingSoonDialog?.let { feature ->
                    ComingSoonDialog(
                        onDismiss = { showComingSoonDialog = null },
                        feature = feature
                    )
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
private fun ParallaxHeaderCard(lesson: Lesson) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp) // Increased height for better visual impact
    ) {
        // Background image with parallax effect
        Image(
            painter = painterResource(id = lesson.imageResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        
        // Gradient overlay with more sophisticated gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.1f),
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startY = 0f,
                        endY = 900f
                    )
                )
        )
        
        // Lesson info with improved layout
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(DesignSystem.Spacing.lg)
        ) {
            // Difficulty chip
            Surface(
                color = when(lesson.difficulty) {
                    LessonDifficulty.BEGINNER -> GreenKurdishLight.copy(alpha = 0.9f)
                    LessonDifficulty.INTERMEDIATE -> YellowKurdish.copy(alpha = 0.9f)
                    LessonDifficulty.ADVANCED -> RedFranceLight.copy(alpha = 0.9f)
                },
                shape = DesignSystem.Shapes.chipShape,
                modifier = Modifier.padding(bottom = DesignSystem.Spacing.md)
            ) {
                Text(
                    text = lesson.difficulty.name.lowercase().replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
            
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(DesignSystem.Spacing.xs))
            
            Text(
                text = lesson.description,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.9f)
            )
            
            // Progress indicator
            Column(
                modifier = Modifier.padding(top = DesignSystem.Spacing.md)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Progress",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White
                    )
                    
                    Text(
                        text = "${(lesson.progress * 100).toInt()}%",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White
                    )
                }
                
                LinearProgressIndicator(
                    progress = { lesson.progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = BlueFranceLight,
                    trackColor = Color.White.copy(alpha = 0.3f)
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    subtitle: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DesignSystem.Spacing.md)
            .padding(top = DesignSystem.Spacing.lg, bottom = DesignSystem.Spacing.sm)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        if (subtitle != null) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabularyCard(vocabularyItem: VocabularyItem) {
    var expanded by remember { mutableStateOf(false) }
    var favorite by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = DesignSystem.Shapes.cardShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // French word
                Text(
                    text = vocabularyItem.french,
                    style = LanguageTypography.frenchVocabulary,
                    color = BlueFrance
                )
                
                // Favorite and pronunciation buttons
                Row {
                    IconButton(
                        onClick = { /* Play pronunciation */ },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Pronunciation",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    
                    IconButton(
                        onClick = { favorite = !favorite },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (favorite) "Remove from favorites" else "Add to favorites",
                            tint = if (favorite) RedFrance else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            
            // Pronunciation
            Text(
                text = "[${vocabularyItem.pronunciation}]",
                style = LanguageTypography.pronunciation,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Translations
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "English:",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = vocabularyItem.english,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Kurdish:",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = vocabularyItem.kurdish,
                        style = LanguageTypography.kurdishTranslation
                    )
                }
            }
            
            // Example sentence (expandable)
            if (vocabularyItem.example.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                
                TextButton(
                    onClick = { expanded = !expanded },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null,
                        tint = BlueFrance,
                        modifier = Modifier.size(16.dp)
                    )
                    
                    Spacer(modifier = Modifier.width(4.dp))
                    
                    Text(
                        text = if (expanded) "Hide example" else "Show example",
                        color = BlueFrance,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                
                AnimatedVisibility(
                    visible = expanded,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Text(
                            text = "Example:",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Text(
                            text = vocabularyItem.example,
                            style = LanguageTypography.example,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ResourceCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = DesignSystem.Shapes.cardShape,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(DesignSystem.Spacing.md),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(DesignSystem.Spacing.xs))
            
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ComingSoonDialog(
    onDismiss: () -> Unit,
    feature: String
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Coming Soon!")
        },
        text = {
            Text("$feature feature will be available in a future update. Stay tuned!")
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
} 