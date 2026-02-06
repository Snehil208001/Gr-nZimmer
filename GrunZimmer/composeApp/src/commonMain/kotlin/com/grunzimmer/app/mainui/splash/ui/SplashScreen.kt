package com.grunzimmer.app.mainui.splash.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    // Added this parameter to fix the error
    onSplashFinished: () -> Unit
) {
    // Navigate automatically after 3 seconds
    LaunchedEffect(Unit) {
        delay(3000)
        onSplashFinished()
    }

    val isDark = isSystemInDarkTheme()
    val backgroundColor = if (isDark) BackgroundDark else Ivory
    val primaryColor = PrimaryGreen
    val secondaryTextColor = if (isDark) TextSecondaryDark else TextSecondaryLight

    // Gradient Background (Top to transparent)
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            primaryColor.copy(alpha = 0.05f),
            Color.Transparent
        ),
        endY = 500f
    )

    // Animation for Progress Bar
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val progressWidth by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "width"
    )
    val progressAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "opacity"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .background(backgroundGradient)
    ) {
        // Center Content
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Logo Mark
            Box(
                modifier = Modifier
                    .size(112.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        if (isDark) Color.White.copy(alpha = 0.05f)
                        else Color.White.copy(alpha = 0.4f)
                    )
                    .border(
                        width = 3.dp,
                        color = if (isDark) primaryColor.copy(alpha = 0.8f) else primaryColor,
                        shape = RoundedCornerShape(32.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Spa,
                    contentDescription = "Logo",
                    tint = primaryColor,
                    modifier = Modifier.size(64.dp)
                )

                // Decorative Dot
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .size(6.dp)
                        .background(primaryColor.copy(alpha = 0.4f), CircleShape)
                )
            }

            // Typography
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "GrünZimmer",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.02).em
                    ),
                    color = if (isDark) Color.White else primaryColor
                )

                Text(
                    text = "URBAN SANCTUARIES",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.2.em
                    ),
                    color = secondaryTextColor
                )
            }
        }

        // Bottom Progress Bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp)
                .width(128.dp)
                .height(4.dp)
                .clip(CircleShape)
                .background(if (isDark) ProgressTrackDark else ProgressTrackLight)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progressWidth)
                    .alpha(progressAlpha)
                    .background(primaryColor, CircleShape)
            )
        }
    }
}

// Extension to help with modifier opacity
fun Modifier.alpha(alpha: Float) = this.then(Modifier.graphicsLayer(alpha = alpha))