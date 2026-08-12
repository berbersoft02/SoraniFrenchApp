package com.berbersoft.soranfrenchapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

/**
 * A centralized design system to maintain consistency throughout the application
 */
object DesignSystem {
    // SPACING
    object Spacing {
        val xs = 4.dp
        val sm = 8.dp
        val md = 16.dp
        val lg = 24.dp
        val xl = 32.dp
        val xxl = 48.dp
    }
    
    // SHAPES
    object Shapes {
        val cardShape = RoundedCornerShape(16.dp)
        val buttonShape = RoundedCornerShape(12.dp)
        val chipShape = RoundedCornerShape(8.dp)
        val searchBarShape = RoundedCornerShape(24.dp)
    }
    
    // ELEVATIONS
    object Elevation {
        val none = 0.dp
        val low = 2.dp
        val medium = 4.dp
        val high = 8.dp
    }
    
    // ANIMATIONS
    object AnimationDuration {
        const val short = 150
        const val medium = 300
        const val long = 500
    }
    
    // CONTENT MAXIMUM WIDTHS
    object ContentWidth {
        val narrow = 600.dp
        val medium = 840.dp
        val wide = 1040.dp
    }
    
    // OPACITY LEVELS
    object Opacity {
        const val disabled = 0.38f
        const val medium = 0.6f
        const val high = 0.87f
        const val full = 1.0f
    }
    
    // CATEGORY COLORS
    object CategoryColors {
        val grammar = BlueFrance
        val vocabulary = RedFrance
        val conversation = GreenKurdish
        val pronunciation = YellowKurdish
    }
} 