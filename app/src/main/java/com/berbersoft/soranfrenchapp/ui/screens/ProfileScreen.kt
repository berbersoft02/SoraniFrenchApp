package com.berbersoft.soranfrenchapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.berbersoft.soranfrenchapp.R
import com.berbersoft.soranfrenchapp.navigation.Screen
import com.berbersoft.soranfrenchapp.ui.theme.BlueFrance
import com.berbersoft.soranfrenchapp.ui.theme.RedFrance
import com.berbersoft.soranfrenchapp.data.UserPreferences
import com.berbersoft.soranfrenchapp.data.DailyGoalManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    var showEditNameDialog by remember { mutableStateOf(false) }
    var showEditGoalDialog by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Profile Section
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.titleLarge
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Name Setting
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Name",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = userPreferences.getUserName() ?: "Not set",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        IconButton(onClick = { showEditNameDialog = true }) {
                            Icon(Icons.Default.Edit, "Edit name")
                        }
                    }
                    
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    
                    // Daily Goal Setting
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Daily Goal",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "${DailyGoalManager.getDailyGoal()} words",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        IconButton(onClick = { showEditGoalDialog = true }) {
                            Icon(Icons.Default.Edit, "Edit goal")
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Statistics Card
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Statistics",
                        style = MaterialTheme.typography.titleLarge
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    StatisticItem(
                        icon = Icons.Default.LocalFireDepartment,
                        label = "Current Streak",
                        value = "0 days" // Streak functionality not implemented yet
                    )
                    
                    StatisticItem(
                        icon = Icons.Default.Star,
                        label = "Today's Progress",
                        value = "${DailyGoalManager.getProgress()} words"
                    )
                }
            }
        }
    }
    
    if (showEditNameDialog) {
        EditNameDialog(
            currentName = userPreferences.getUserName() ?: "",
            onDismiss = { showEditNameDialog = false },
            onNameChanged = { newName ->
                userPreferences.setUserName(newName)
                showEditNameDialog = false
            }
        )
    }
    
    if (showEditGoalDialog) {
        EditGoalDialog(
            currentGoal = DailyGoalManager.getDailyGoal(),
            onDismiss = { showEditGoalDialog = false },
            onGoalChanged = { newGoal ->
                DailyGoalManager.setDailyGoal(newGoal)
                showEditGoalDialog = false
            }
        )
    }
}

@Composable
private fun StatisticItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun EditNameDialog(
    currentName: String,
    onDismiss: () -> Unit,
    onNameChanged: (String) -> Unit
) {
    var name by remember { mutableStateOf(currentName) }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Name") },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                singleLine = true
            )
        },
        confirmButton = {
            TextButton(
                onClick = { onNameChanged(name) },
                enabled = name.isNotBlank()
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
private fun EditGoalDialog(
    currentGoal: Int,
    onDismiss: () -> Unit,
    onGoalChanged: (Int) -> Unit
) {
    var goal by remember { mutableStateOf(currentGoal.toString()) }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Daily Goal") },
        text = {
            OutlinedTextField(
                value = goal,
                onValueChange = { if (it.all { char -> char.isDigit() }) goal = it },
                label = { Text("Words per day") },
                singleLine = true
            )
        },
        confirmButton = {
            TextButton(
                onClick = { goal.toIntOrNull()?.let { onGoalChanged(it) } },
                enabled = goal.toIntOrNull() != null && goal.toIntOrNull()!! > 0
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ProfileOption(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = BlueFrance
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun StatisticItem(value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            color = BlueFrance
        )
        
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AchievementItem(
    title: String,
    description: String,
    achieved: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(
                id = if (achieved) R.drawable.ic_achievement_unlocked else R.drawable.ic_achievement_locked
            ),
            contentDescription = null,
            tint = if (achieved) RedFrance else MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(40.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = if (achieved) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.outline
            )
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = if (achieved) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.outline
            )
        }
    }
} 