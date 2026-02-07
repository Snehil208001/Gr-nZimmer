package com.grunzimmer.app.mainui.services.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.BackgroundLight
import com.grunzimmer.app.presentation.theme.LoginPrimary
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.inspiration_1
import grunzimmer.composeapp.generated.resources.vertical_garden
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun VerticalGardenScreen(
    onBackClick: () -> Unit,
    onScheduleVisitClick: () -> Unit
) {
    // UI State
    var wallLocation by remember { mutableStateOf("Indoor") }
    var sunlightExposure by remember { mutableStateOf("Indirect Bright") }
    var budgetValue by remember { mutableStateOf(50000f) }

    // Custom Colors for this screen based on HTML design
    val BackgroundWarm = Color(0xFFFDFDF9)
    val TextColor = Color(0xFF121714)
    val SubTextColor = Color(0xFF4B5563)

    Scaffold(
        containerColor = BackgroundWarm,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundWarm.copy(alpha = 0.95f))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Transparent)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = LoginPrimary
                    )
                }
                Text(
                    text = "Vertical Garden Setup",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColor,
                    modifier = Modifier.weight(1f),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.size(40.dp))
            }
        },
        bottomBar = {
            // Gradient Bottom Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                BackgroundWarm.copy(alpha = 0.0f),
                                BackgroundWarm.copy(alpha = 0.9f),
                                BackgroundWarm
                            )
                        )
                    )
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Packages starting from ₹30,000",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = SubTextColor,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Button(
                        onClick = onScheduleVisitClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .shadow(8.dp, spotColor = LoginPrimary.copy(alpha = 0.4f)),
                        shape = RoundedCornerShape(50), // Fully rounded
                        colors = ButtonDefaults.buttonColors(containerColor = LoginPrimary)
                    ) {
                        Text(
                            text = "Request Site Visit",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(20.dp))
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // 1. Hero Illustration
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .aspectRatio(4f / 3f)
                    .clip(RoundedCornerShape(32.dp))
                    .border(1.dp, LoginPrimary.copy(alpha = 0.1f), RoundedCornerShape(32.dp))
            ) {
                Image(
                    painter = painterResource(Res.drawable.vertical_garden), // Using different inspiration image
                    contentDescription = "Vertical Garden",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.verticalGradient(listOf(Color.Black.copy(alpha = 0.1f), Color.Transparent)))
                )
            }

            // 2. Service Overview
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                Text(
                    text = "Service Overview",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColor,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = "Transform your bare walls into living art. Our vertical gardens improve air quality, reduce ambient temperature, and bring the soothing essence of nature directly into your urban home.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = SubTextColor,
                    lineHeight = 24.sp
                )
            }

            // 3. What's Included (Grid Layout)
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = "What's Included",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColor,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Grid 2x2
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        GridIncludedItem(
                            icon = Icons.Default.AssignmentTurnedIn,
                            title = "Site Inspection",
                            subtitle = "Free expert visit",
                            modifier = Modifier.weight(1f)
                        )
                        GridIncludedItem(
                            icon = Icons.Default.LocalFlorist,
                            title = "Plant Selection",
                            subtitle = "Curated species",
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        GridIncludedItem(
                            icon = Icons.Default.WaterDrop,
                            title = "Auto Irrigation",
                            subtitle = "Drip system setup",
                            modifier = Modifier.weight(1f)
                        )
                        GridIncludedItem(
                            icon = Icons.Default.Build,
                            title = "Maintenance",
                            subtitle = "1-month included",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // 4. Customization Form
            Column(modifier = Modifier.padding(16.dp)) {
                // Wall Location
                Text("Wall Location", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextColor, modifier = Modifier.padding(bottom = 12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Pass weight modifier here, inside the Row scope
                    val weightModifier = Modifier.weight(1f)
                    VerticalGardenChip("Indoor", wallLocation == "Indoor", weightModifier) { wallLocation = "Indoor" }
                    VerticalGardenChip("Outdoor", wallLocation == "Outdoor", weightModifier) { wallLocation = "Outdoor" }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Sunlight Exposure
                Text("Sunlight Exposure", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextColor, modifier = Modifier.padding(bottom = 12.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val weightModifier = Modifier.weight(1f)
                    VerticalGardenChipSmall("Low Light", sunlightExposure == "Low Light", weightModifier) { sunlightExposure = "Low Light" }
                    VerticalGardenChipSmall("Indirect Bright", sunlightExposure == "Indirect Bright", weightModifier) { sunlightExposure = "Indirect Bright" }
                    VerticalGardenChipSmall("Direct Sun", sunlightExposure == "Direct Sun", weightModifier) { sunlightExposure = "Direct Sun" }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Budget Range
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Estimated Budget", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextColor)
                    Text("₹30k - ₹${budgetValue.roundToInt()}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = LoginPrimary)
                }

                Slider(
                    value = budgetValue,
                    onValueChange = { budgetValue = it },
                    valueRange = 30000f..100000f,
                    colors = SliderDefaults.colors(
                        thumbColor = LoginPrimary,
                        activeTrackColor = LoginPrimary,
                        inactiveTrackColor = Color(0xFFD3D6D2)
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("₹30k", fontSize = 12.sp, color = Color.Gray)
                    Text("₹1L+", fontSize = 12.sp, color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Upload Photo
                Text("Upload Wall Photo (Optional)", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextColor, modifier = Modifier.padding(bottom = 12.dp))
                DashedUploadBoxVertical()

                // Bottom spacing for sticky footer
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

// --- Specific Components for Vertical Garden Screen ---

@Composable
fun GridIncludedItem(icon: ImageVector, title: String, subtitle: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFEBECE8), RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(LoginPrimary.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = LoginPrimary,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF121714), lineHeight = 18.sp)
            Text(text = subtitle, fontSize = 12.sp, color = Color(0xFF6B7280), modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Composable
fun VerticalGardenChip(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier, // Accepts modifier (e.g., weight) from parent
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(if (isSelected) LoginPrimary else Color.Transparent)
            .border(1.dp, if (isSelected) LoginPrimary else Color(0xFFD3D6D2), RoundedCornerShape(50))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) Color.White else Color(0xFF4B5563)
        )
    }
}

@Composable
fun VerticalGardenChipSmall(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(if (isSelected) LoginPrimary.copy(alpha = 0.1f) else Color.White)
            .border(1.dp, if (isSelected) LoginPrimary else Color(0xFFD3D6D2), RoundedCornerShape(50))
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSelected) LoginPrimary else Color(0xFF4B5563),
            maxLines = 1,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun DashedUploadBoxVertical() {
    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .drawBehind {
                drawRoundRect(
                    color = Color(0xFFD3D6D2),
                    style = stroke,
                    cornerRadius = CornerRadius(16.dp.toPx())
                )
            }
            .clickable { /* Handle Upload */ },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEBECE8)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.AddAPhoto, null, tint = Color.Gray)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Tap to upload", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = LoginPrimary)
            Text("Help us prepare better for the visit", fontSize = 12.sp, color = Color.Gray)
        }
    }
}