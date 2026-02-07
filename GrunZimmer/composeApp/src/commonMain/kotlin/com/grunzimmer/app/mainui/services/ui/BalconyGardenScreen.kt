package com.grunzimmer.app.mainui.services.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.BackgroundLight
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.balcony
import grunzimmer.composeapp.generated.resources.inspiration_2
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun BalconyGardenScreen(
    onBackClick: () -> Unit,
    onScheduleVisitClick: () -> Unit
) {
    var selectedSize by remember { mutableStateOf("Small") }
    var selectedSunlight by remember { mutableStateOf("Low") }
    var budgetValue by remember { mutableStateOf(15000f) }

    val PrimaryColor = Color(0xFF1F6F43)
    val TextColor = Color(0xFF121714)
    val SubTextColor = Color(0xFF688274)

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundLight.copy(alpha = 0.95f))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick, modifier = Modifier.size(40.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = TextColor)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text("Balcony Setup", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextColor)
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(40.dp))
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.95f))
                    .padding(16.dp)
                    .shadow(elevation = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text("Starting from ", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = SubTextColor)
                        Text("₹8,000", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
                    }
                    Box(modifier = Modifier.background(PrimaryColor.copy(alpha = 0.1f), RoundedCornerShape(50)).padding(horizontal = 8.dp, vertical = 2.dp)) {
                        Text("Free Cancellation", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
                    }
                }
                Button(
                    onClick = onScheduleVisitClick,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Icon(Icons.Default.CalendarMonth, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Request Site Visit", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp).aspectRatio(16f / 10f).clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(Res.drawable.balcony),
                    contentDescription = "Balcony Garden",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.3f)))))
            }

            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text("Service Overview", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextColor, modifier = Modifier.padding(bottom = 8.dp))
                Text("Transform your compact outdoor space into a thriving green sanctuary tailored to Patna's climate.", fontSize = 16.sp, color = SubTextColor, lineHeight = 24.sp)
            }

            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                Text("What's Included", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextColor, modifier = Modifier.padding(bottom = 16.dp))
                val items = listOf(
                    DetailedIncludedItem(Icons.Default.Agriculture, "Soil & Fertilizers", "Premium organic mix"),
                    DetailedIncludedItem(Icons.Default.LocalFlorist, "Plant Selection", "Air purifying & local species"),
                    DetailedIncludedItem(Icons.Default.Weekend, "Planters & Stands", "Durable weather-proof designs")
                )
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items.forEach { DetailedIncludedItemRow(it, PrimaryColor, TextColor, SubTextColor) }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text("Customize Your Oasis", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextColor, modifier = Modifier.padding(bottom = 16.dp))

                Text("Balcony Size", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextColor, modifier = Modifier.padding(bottom = 12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ChipButton("Small (<40 sqft)", selectedSize == "Small") { selectedSize = "Small" }
                    ChipButton("Medium", selectedSize == "Medium") { selectedSize = "Medium" }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text("Sunlight Availability", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextColor, modifier = Modifier.padding(bottom = 12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    SunlightChip("Low", Icons.Default.WbTwilight, selectedSunlight == "Low") { selectedSunlight = "Low" }
                    SunlightChip("Partial", Icons.Outlined.WbSunny, selectedSunlight == "Partial") { selectedSunlight = "Partial" }
                    SunlightChip("Direct", Icons.Default.WbSunny, selectedSunlight == "Direct") { selectedSunlight = "Direct" }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Row(Modifier.fillMaxWidth().padding(bottom = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
                    Text("Budget Range", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextColor)
                    Text("₹8k - ₹${budgetValue.roundToInt()}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
                }
                Slider(value = budgetValue, onValueChange = { budgetValue = it }, valueRange = 5000f..50000f, colors = SliderDefaults.colors(thumbColor = PrimaryColor, activeTrackColor = PrimaryColor))

                Spacer(modifier = Modifier.height(24.dp))
                Text("Upload Balcony Photos", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextColor, modifier = Modifier.padding(bottom = 12.dp))
                DashedUploadBox(PrimaryColor)
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}