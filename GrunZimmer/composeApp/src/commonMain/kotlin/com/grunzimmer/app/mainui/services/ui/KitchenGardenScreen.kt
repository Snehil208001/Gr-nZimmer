package com.grunzimmer.app.mainui.services.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material.icons.filled.Compost
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.grunzimmer.app.presentation.theme.LoginPrimary
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.inspiration_2
import grunzimmer.composeapp.generated.resources.kitchen_garden
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun KitchenGardenScreen(
    onBackClick: () -> Unit,
    onScheduleVisitClick: () -> Unit
) {
    // UI State
    var selectedLocation by remember { mutableStateOf("Balcony") }
    var gardenSize by remember { mutableStateOf(80f) }
    var selectedBudget by remember { mutableStateOf("₹10k - 25k") }

    // Veg preferences (Multiple selection)
    val vegOptions = listOf("Leafy Greens", "Root Veg", "Herbs", "Fruits", "Medicinal")
    val selectedVegs = remember { mutableStateOf(setOf("Leafy Greens")) }

    // Colors matching HTML design
    val PrimaryColor = Color(0xFF1F6F43)
    val PrimaryLight = Color(0xFFE4F0E9)
    val BackgroundWarm = Color(0xFFF9F9F7)
    val SurfaceColor = Color.White
    val TextColor = Color(0xFF111827) // gray-900
    val SubTextColor = Color(0xFF4B5563) // gray-600

    Scaffold(
        containerColor = BackgroundWarm,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundWarm.copy(alpha = 0.9f))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextColor
                    )
                }

                Text(
                    text = "Kitchen Garden",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColor
                )

                IconButton(
                    onClick = { /* Favorite */ },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = TextColor
                    )
                }
            }
        },
        bottomBar = {
            // Sticky Bottom Bar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 16.dp, spotColor = Color.Black.copy(alpha = 0.05f))
                    .background(SurfaceColor)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(modifier = Modifier.weight(0.4f)) {
                        Text(
                            text = "TOTAL ESTIMATE",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF9CA3AF), // gray-400
                            letterSpacing = 0.5.sp
                        )
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "from ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = SubTextColor,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            Text(
                                text = "₹10,000",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextColor
                            )
                        }
                    }

                    Button(
                        onClick = onScheduleVisitClick,
                        modifier = Modifier
                            .weight(0.6f)
                            .height(56.dp)
                            .shadow(10.dp, spotColor = PrimaryColor.copy(alpha = 0.3f)),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                    ) {
                        Text("Request Visit", fontSize = 16.sp, fontWeight = FontWeight.Bold)
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
                    .height(256.dp)
                    .background(PrimaryLight)
            ) {
                // Pattern overlay (simulated with simple background)
                Box(Modifier.fillMaxSize().background(PrimaryLight))

                Image(
                    painter = painterResource(Res.drawable.kitchen_garden), // Using existing resource
                    contentDescription = "Kitchen Garden",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 32.dp)
                )

                // Gradient at bottom to blend with background
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Brush.verticalGradient(listOf(Color.Transparent, BackgroundWarm)))
                )
            }

            // 2. Service Overview Card (Overlapping)
            Box(modifier = Modifier.padding(horizontal = 20.dp).offset(y = (-32).dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceColor, RoundedCornerShape(16.dp))
                        .border(1.dp, Color(0xFFF3F4F6), RoundedCornerShape(16.dp))
                        .padding(20.dp)
                        .shadow(2.dp, shape = RoundedCornerShape(16.dp), spotColor = Color.Black.copy(alpha = 0.05f)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Fresh, Chemical-Free Food",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryColor,
                        modifier = Modifier.padding(bottom = 8.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    Text(
                        text = "Grow your own organic produce. We design productive spaces that yield fresh ingredients right outside your door.",
                        fontSize = 13.sp,
                        color = SubTextColor,
                        lineHeight = 18.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }

            // 3. What's Included
            Column(modifier = Modifier.padding(horizontal = 20.dp).offset(y = (-16).dp)) {
                Text(
                    text = "What's Included",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColor,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Grid
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        val weightModifier = Modifier.weight(1f)
                        KitchenIncludedItem(
                            icon = Icons.Default.SquareFoot,
                            title = "Site Assessment",
                            primaryColor = PrimaryColor,
                            modifier = weightModifier
                        )
                        KitchenIncludedItem(
                            icon = Icons.Default.Compost,
                            title = "Soil Mix Prep",
                            primaryColor = PrimaryColor,
                            modifier = weightModifier
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        val weightModifier = Modifier.weight(1f)
                        KitchenIncludedItem(
                            icon = Icons.Default.Restaurant,
                            title = "Veg Selection",
                            primaryColor = PrimaryColor,
                            modifier = weightModifier
                        )
                        KitchenIncludedItem(
                            icon = Icons.Default.MenuBook,
                            title = "Care Guide",
                            primaryColor = PrimaryColor,
                            modifier = weightModifier
                        )
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 24.dp, horizontal = 20.dp),
                thickness = 1.dp,
                color = Color(0xFFE5E7EB)
            )

            // 4. User Inputs
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                // Location
                SectionHeader("Preferred Location")
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    val weightModifier = Modifier.weight(1f)
                    LocationButton(
                        text = "Balcony",
                        isSelected = selectedLocation == "Balcony",
                        primaryColor = PrimaryColor,
                        modifier = weightModifier
                    ) { selectedLocation = "Balcony" }
                    LocationButton(
                        text = "Terrace",
                        isSelected = selectedLocation == "Terrace",
                        primaryColor = PrimaryColor,
                        modifier = weightModifier
                    ) { selectedLocation = "Terrace" }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Garden Size
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    SectionHeader("Garden Size", noPadding = true)
                    Text(
                        text = "${gardenSize.roundToInt()} sq ft",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryColor
                    )
                }

                Slider(
                    value = gardenSize,
                    onValueChange = { gardenSize = it },
                    valueRange = 20f..500f,
                    colors = SliderDefaults.colors(
                        thumbColor = PrimaryColor,
                        activeTrackColor = PrimaryColor,
                        inactiveTrackColor = Color(0xFFE5E7EB)
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("20 sq ft", fontSize = 12.sp, color = Color.Gray)
                    Text("500+ sq ft", fontSize = 12.sp, color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Veg Preference
                SectionHeader("What to Grow?")
                FlowRowLikeLayout(
                    items = vegOptions,
                    selectedItems = selectedVegs.value,
                    primaryColor = PrimaryColor
                ) { item ->
                    val current = selectedVegs.value.toMutableSet()
                    if (current.contains(item)) current.remove(item) else current.add(item)
                    selectedVegs.value = current
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Budget Range
                SectionHeader("Budget Range")
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    listOf("₹10k - 25k", "₹25k - 50k", "₹50k+").forEach { budget ->
                        val isSelected = selectedBudget == budget
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(if (isSelected) PrimaryColor else SurfaceColor)
                                .border(1.dp, if (isSelected) PrimaryColor else Color(0xFFE5E7EB), RoundedCornerShape(50))
                                .clickable { selectedBudget = budget }
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = budget,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (isSelected) Color.White else SubTextColor
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Upload Photo
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 12.dp)) {
                    SectionHeader("Upload Space Photo", noPadding = true)
                    Spacer(Modifier.width(8.dp))
                    Text("(Optional)", fontSize = 14.sp, color = Color.Gray)
                }
                DashedUploadBoxKitchen(PrimaryColor)

                // Spacer for scroll content vs bottom bar
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

// --- Components for Kitchen Garden Screen ---

@Composable
fun SectionHeader(text: String, noPadding: Boolean = false) {
    Text(
        text = text.uppercase(),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF111827),
        letterSpacing = 0.5.sp,
        modifier = if (noPadding) Modifier else Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun KitchenIncludedItem(icon: ImageVector, title: String, primaryColor: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFF3F4F6), RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(primaryColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = primaryColor, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF374151),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun LocationButton(text: String, isSelected: Boolean, primaryColor: Color, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(if (isSelected) primaryColor else Color.White)
            .border(1.dp, if (isSelected) primaryColor else Color(0xFFE5E7EB), RoundedCornerShape(50))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) Color.White else Color(0xFF4B5563)
        )
    }
}

@Composable
fun FlowRowLikeLayout(items: List<String>, selectedItems: Set<String>, primaryColor: Color, onItemClick: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        val rows = items.chunked(3)
        rows.forEach { rowItems ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                rowItems.forEach { item ->
                    val isSelected = selectedItems.contains(item)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(if (isSelected) primaryColor.copy(alpha = 0.1f) else Color.White)
                            .border(1.dp, if (isSelected) primaryColor else Color(0xFFE5E7EB), RoundedCornerShape(50))
                            .clickable { onItemClick(item) }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = item,
                            fontSize = 14.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                            color = if (isSelected) primaryColor else Color(0xFF4B5563)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DashedUploadBoxKitchen(primaryColor: Color) {
    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .drawBehind {
                drawRoundRect(
                    color = Color(0xFFD1D5DB), // gray-300
                    style = stroke,
                    cornerRadius = CornerRadius(12.dp.toPx())
                )
            }
            .clickable { /* Handle Upload */ }
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF9FAFB)), // gray-50
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.AddAPhoto, null, tint = Color(0xFF9CA3AF)) // gray-400
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Tap to upload image", fontSize = 14.sp, color = Color(0xFF6B7280)) // gray-500
        }
    }
}