package com.berbersoft.soranfrenchapp.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.berbersoft.soranfrenchapp.R
import com.berbersoft.soranfrenchapp.data.DummyData
import com.berbersoft.soranfrenchapp.data.VocabularyItem
import com.berbersoft.soranfrenchapp.data.TextToSpeechManager
import com.berbersoft.soranfrenchapp.ui.theme.BlueFrance
import com.berbersoft.soranfrenchapp.ui.theme.DesignSystem
import com.berbersoft.soranfrenchapp.ui.theme.LanguageTypography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DictionaryScreen(
    navController: NavController,
    ttsManager: TextToSpeechManager = TextToSpeechManager.getInstance(LocalContext.current)
) {
    var selectedLessonId by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    
    val allVocabulary = remember { DummyData.getAllVocabulary() }
    val allPhrases = remember { DummyData.getAllPhrases() }
    
    // Combine vocabulary and phrases into a single list for dictionary display
    val combinedList = remember(allVocabulary, allPhrases) {
        val vocabAsWords = allVocabulary.map { vocab ->
            VocabularyItem(
                id = vocab.id,
                french = vocab.french,
                english = vocab.english,
                kurdish = vocab.kurdish,
                pronunciation = vocab.pronunciation,
                example = vocab.example,
                lessonId = vocab.lessonId
            )
        }
        val phrasesAsWords = allPhrases.map { phrase ->
            VocabularyItem(
                id = phrase.id,
                french = phrase.french,
                english = phrase.english,
                kurdish = phrase.kurdish,
                pronunciation = phrase.pronunciation,
                example = "",
                lessonId = "phrases"
            )
        }
        vocabAsWords + phrasesAsWords
    }
    
    // Filter by lesson and search query
    val filteredResults = remember(searchQuery, selectedLessonId) {
        val baseList = if (selectedLessonId != null) {
            combinedList.filter { it.lessonId == selectedLessonId }
        } else if (searchQuery.isBlank()) {
            combinedList
        } else {
            combinedList.filter {
                it.french.contains(searchQuery, ignoreCase = true) ||
                it.english.contains(searchQuery, ignoreCase = true) ||
                it.kurdish.contains(searchQuery, ignoreCase = true)
            }
        }

        // Group by lesson
        baseList.groupBy { item ->
            DummyData.recentLessons.find { it.id == item.lessonId }?.title ?: if (item.lessonId == "phrases") "Common Phrases" else "Other"
        }
    }
    
    Scaffold(
        topBar = {
            Column {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = DesignSystem.Spacing.md, vertical = DesignSystem.Spacing.sm),
                    horizontalArrangement = Arrangement.spacedBy(DesignSystem.Spacing.sm)
                ) {
                    items(DummyData.recentLessons) { lesson ->
                        FilterChip(
                            selected = selectedLessonId == lesson.id,
                            onClick = {
                                selectedLessonId = if (selectedLessonId == lesson.id) null else lesson.id
                                searchQuery = ""
                            },
                            label = { Text(lesson.title) }
                        )
                    }
                }
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = {
                        keyboardController?.hide()
                        isSearchActive = false
                    },
                    active = isSearchActive,
                    onActiveChange = { isSearchActive = it },
                    placeholder = { Text("Search words...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear search"
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = DesignSystem.Spacing.md)
                        .focusRequester(focusRequester),
                    shape = DesignSystem.Shapes.searchBarShape,
                    colors = SearchBarDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        inputFieldColors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                ) {
                    // Search suggestions
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(DesignSystem.Spacing.sm)
                    ) {
                        filteredResults.entries.take(2).forEach { (category, words) ->
                            words.take(3).forEach { word ->
                                item {
                                    ListItem(
                                        headlineContent = { Text(word.french) },
                                        supportingContent = { 
                                            Text(
                                                text = "${word.english} • $category",
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        },
                                        leadingContent = {
                                            Icon(
                                                imageVector = Icons.Default.Search,
                                                contentDescription = null
                                            )
                                        },
                                        modifier = Modifier.clickable {
                                            searchQuery = word.french
                                            isSearchActive = false
                                            keyboardController?.hide()
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        if (filteredResults.isEmpty()) {
            EmptySearchResult(searchQuery)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = DesignSystem.Spacing.sm),
                contentPadding = PaddingValues(bottom = DesignSystem.Spacing.xl),
                verticalArrangement = Arrangement.spacedBy(DesignSystem.Spacing.md)
            ) {
                filteredResults.forEach { (category, words) ->
                    item {
                        Text(
                            text = category,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = DesignSystem.Spacing.md)
                                .padding(bottom = DesignSystem.Spacing.sm, top = DesignSystem.Spacing.md)
                        )
                    }
                    
                    items(words) { word ->
                        DictionaryWordCard(word, ttsManager)
                    }
                }
            }
        }
    }
}

@Composable
fun EmptySearchResult(query: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(DesignSystem.Spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.SearchOff,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
        )
        
        Spacer(modifier = Modifier.height(DesignSystem.Spacing.md))
        
        Text(
            text = "No results found for \"$query\"",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(DesignSystem.Spacing.sm))
        
        Text(
            text = "Try searching for another word or phrase",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryWordCard(
    word: VocabularyItem,
    ttsManager: TextToSpeechManager
) {
    var expanded by remember { mutableStateOf(false) }
    val isPhrase = word.lessonId == "phrases"
    
    ElevatedCard(
        onClick = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DesignSystem.Spacing.md)
            .animateContentSize(),
        shape = DesignSystem.Shapes.cardShape,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = if (expanded) 8.dp else 4.dp,
            pressedElevation = 12.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(DesignSystem.Spacing.md)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = word.french,
                        style = LanguageTypography.frenchVocabulary.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = BlueFrance
                    )
                    
                    Text(
                        text = word.pronunciation,
                        style = LanguageTypography.pronunciation.copy(
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                            alpha = 0.8f
                        )
                    )
                }
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(DesignSystem.Spacing.sm),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledTonalIconButton(
                        onClick = { ttsManager.speakFrench(word.french) },
                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.VolumeUp,
                            contentDescription = "Listen French"
                        )
                    }
                    
                    FilledTonalIconButton(
                        onClick = { ttsManager.speakKurdish(word.kurdish) },
                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.VolumeUp,
                            contentDescription = "Listen Kurdish"
                        )
                    }
                    
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = if (expanded) "Show less" else "Show more"
                        )
                    }
                }
            }
            
            if (expanded) {
                Divider(
                    modifier = Modifier.padding(vertical = DesignSystem.Spacing.md)
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "English:",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Text(
                            text = word.english,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(DesignSystem.Spacing.md))
                    
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Kurdish:",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                        
                        Text(
                            text = word.kurdish,
                            style = LanguageTypography.kurdishTranslation,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                
                if (word.example.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(DesignSystem.Spacing.md))
                    
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                        shape = DesignSystem.Shapes.chipShape,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = DesignSystem.Spacing.xs)
                    ) {
                        Column(modifier = Modifier.padding(DesignSystem.Spacing.sm)) {
                            Text(
                                text = "Example:",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            
                            Text(
                                text = word.example,
                                style = LanguageTypography.example,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                } else if (isPhrase) {
                    Surface(
                        color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
                        shape = DesignSystem.Shapes.chipShape,
                        modifier = Modifier
                            .padding(vertical = DesignSystem.Spacing.xs)
                    ) {
                        Text(
                            text = "Common Phrase",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = DesignSystem.Spacing.md),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = { /* Add to favorites or personal dictionary */ },
                        shape = DesignSystem.Shapes.buttonShape
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Save")
                    }
                }
            }
        }
    }
}
