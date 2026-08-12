package com.berbersoft.soranfrenchapp.ui.components

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) 