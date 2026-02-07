package com.grunzimmer.app.mainui.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Deck
import androidx.compose.material.icons.filled.Window
import androidx.compose.material.icons.filled.Yard
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.LoginPrimary

@Composable
fun PropertyTypeScreen(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    var selectedProperty by remember { mutableStateOf("Balcony") }

    // Colors from HTML design
    val PrimaryColor = Color(0xFF1F6F43)
    val BackgroundLight = Color(0xFFF9F7F2) // Earthy off-white
    val SageGreen = Color(0xFFA8CBB7)
    val EarthBeige = Color(0xFFEFE9D8)
    val TextMain = Color(0xFF121714)
    val TextMuted = Color(0xFF1F6F43).copy(alpha = 0.8f) // Primary at 80%

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Transparent)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = PrimaryColor
                    )
                }
                Text(
                    text = "Select Property Type",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    modifier = Modifier.weight(1f),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.size(48.dp))
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .padding(bottom = 12.dp)
            ) {
                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(10.dp, spotColor = PrimaryColor.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text(
                        text = "Save Property Type",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            PropertyOptionCard(
                title = "Terrace",
                subtitle = "Open rooftop space",
                icon = Icons.Default.Deck,
                isSelected = selectedProperty == "Terrace",
                selectedColor = SageGreen,
                unselectedColor = EarthBeige,
                primaryColor = PrimaryColor,
                textColor = TextMain,
                mutedColor = TextMuted
            ) { selectedProperty = "Terrace" }

            Spacer(modifier = Modifier.height(20.dp))

            PropertyOptionCard(
                title = "Balcony",
                subtitle = "Attached outdoor area",
                icon = Icons.Default.Window, // Fallback for Balcony icon
                isSelected = selectedProperty == "Balcony",
                selectedColor = SageGreen,
                unselectedColor = EarthBeige,
                primaryColor = PrimaryColor,
                textColor = TextMain,
                mutedColor = TextMuted
            ) { selectedProperty = "Balcony" }

            Spacer(modifier = Modifier.height(20.dp))

            PropertyOptionCard(
                title = "Independent House",
                subtitle = "Ground level garden or yard",
                icon = Icons.Default.Yard,
                isSelected = selectedProperty == "Independent House",
                selectedColor = SageGreen,
                unselectedColor = EarthBeige,
                primaryColor = PrimaryColor,
                textColor = TextMain,
                mutedColor = TextMuted
            ) { selectedProperty = "Independent House" }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "We curate plant recommendations based on the sunlight and space available in your property type.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF688274),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun PropertyOptionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    isSelected: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    primaryColor: Color,
    textColor: Color,
    mutedColor: Color,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) selectedColor else unselectedColor
    val borderColor = if (isSelected) primaryColor else primaryColor.copy(alpha = 0.2f)
    val shadowElevation = if (isSelected) 8.dp else 0.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp)) // rounded-[2rem]
            .background(bgColor)
            .border(2.dp, borderColor, RoundedCornerShape(32.dp))
            .clickable(onClick = onClick)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icon Circle
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = if (isSelected) 0.8f else 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = primaryColor,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Text(
                text = subtitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = mutedColor
            )
        }

        // Checkmark (Top Right)
        if (isSelected) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(primaryColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}