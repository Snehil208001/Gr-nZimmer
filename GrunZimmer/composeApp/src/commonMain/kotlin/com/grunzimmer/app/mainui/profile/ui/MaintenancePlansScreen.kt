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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Compost
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.PestControl
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.outlined.LocalFlorist
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.LoginPrimary

@Composable
fun MaintenancePlansScreen(
    onBackClick: () -> Unit,
    onSelectPlan: (String) -> Unit
) {
    var selectedPlan by remember { mutableStateOf("Standard Care") }

    // Colors matching HTML design
    val PrimaryColor = Color(0xFF1F6F43)
    val BackgroundLight = Color(0xFFF6F8F7)
    val SurfaceSage = Color(0xFFEEF5F1)
    val SurfaceBeige = Color(0xFFF5F2EB)
    val TextMain = Color(0xFF121714)
    val BorderColor = Color(0xFFDDE4E0)

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            // Sticky Top Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundLight.copy(alpha = 0.9f))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .padding(top = 8.dp) // extra padding as per design
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
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
                            tint = PrimaryColor
                        )
                    }
                    Text(
                        text = "Maintenance Plans",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryColor,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(40.dp))
                }
            }
        },
        bottomBar = {
            // Sticky Footer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.8f))
                    .border(1.dp, Color(0xFFF3F4F6)) // Light border top
                    .padding(16.dp)
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = PrimaryColor.copy(alpha = 0.7f),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "Plans can be paused or changed anytime.",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = PrimaryColor.copy(alpha = 0.7f)
                    )
                }

                Button(
                    onClick = { onSelectPlan(selectedPlan) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(10.dp, spotColor = PrimaryColor.copy(alpha = 0.3f)),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text(
                        text = "Select $selectedPlan",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(20.dp))
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            // Intro Text
            Text(
                text = "Choose a care plan tailored to your garden's needs. Upgrade or pause anytime.",
                fontSize = 14.sp,
                color = Color(0xFF4B5563), // gray-600
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Basic Plan
            PlanCard(
                title = "Basic Care",
                subtitle = "Essential Maintenance",
                price = "₹1,500",
                isSelected = selectedPlan == "Basic Care",
                backgroundColor = SurfaceBeige,
                borderColor = BorderColor,
                features = listOf(
                    PlanFeature(Icons.Default.WaterDrop, "Monthly watering check"),
                    PlanFeature(Icons.Default.ContentCut, "Basic pruning & trimming"),
                    PlanFeature(Icons.Default.HealthAndSafety, "Visual health assessment")
                ),
                onClick = { selectedPlan = "Basic Care" }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Standard Plan (Recommended)
            Box {
                PlanCard(
                    title = "Standard Care",
                    subtitle = "Most Popular",
                    price = "₹3,000",
                    isSelected = selectedPlan == "Standard Care",
                    backgroundColor = SurfaceSage,
                    borderColor = PrimaryColor,
                    features = listOf(
                        PlanFeature(Icons.Default.CalendarMonth, "Bi-weekly specialist visits"),
                        PlanFeature(Icons.Default.Compost, "Organic fertilization"),
                        PlanFeature(Icons.Default.PestControl, "Pest control treatment"),
                        PlanFeature(Icons.Default.Grass, "Soil aeration service")
                    ),
                    isRecommended = true, // Special styling logic inside
                    onClick = { selectedPlan = "Standard Care" }
                )
                // Recommended Badge Overlay
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = (-12).dp)
                        .background(PrimaryColor, RoundedCornerShape(50))
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                        .shadow(2.dp, RoundedCornerShape(50))
                ) {
                    Text(
                        text = "RECOMMENDED",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Premium Plan
            PlanCard(
                title = "Premium Care",
                subtitle = "All-Inclusive",
                price = "₹5,000",
                isSelected = selectedPlan == "Premium Care",
                backgroundColor = SurfaceBeige,
                borderColor = BorderColor,
                features = listOf(
                    PlanFeature(Icons.Default.Star, "Weekly dedicated visits"),
                    PlanFeature(Icons.Outlined.LocalFlorist, "Seasonal repotting service"), // potted_plant replacement
                    PlanFeature(Icons.Default.VerifiedUser, "Plant replacement guarantee"),
                    PlanFeature(Icons.Default.SupportAgent, "Priority support hotline")
                ),
                onClick = { selectedPlan = "Premium Care" }
            )

            // Bottom spacing
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// --- Data Classes & Components ---

data class PlanFeature(val icon: ImageVector, val text: String)

@Composable
fun PlanCard(
    title: String,
    subtitle: String,
    price: String,
    isSelected: Boolean,
    backgroundColor: Color,
    borderColor: Color,
    features: List<PlanFeature>,
    isRecommended: Boolean = false,
    onClick: () -> Unit
) {
    val activeBorderColor = if (isSelected) LoginPrimary else borderColor
    val activeBorderWidth = if (isSelected || isRecommended) 2.dp else 1.dp
    val shadowElevation = if (isSelected) 8.dp else 0.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(shadowElevation, RoundedCornerShape(16.dp), spotColor = LoginPrimary.copy(alpha = 0.1f))
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(activeBorderWidth, activeBorderColor, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(24.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF121714))
                Text(
                    subtitle.uppercase(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = if(isRecommended) LoginPrimary else Color.Gray,
                    letterSpacing = 0.5.sp
                )
            }

            // Selection Indicator
            val indicatorBorder = if (isSelected || isRecommended) LoginPrimary else Color.LightGray
            val indicatorBg = if (isSelected) LoginPrimary else Color.Transparent

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(indicatorBg)
                    .border(2.dp, indicatorBorder, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    // Inner dot or check
                    if (isRecommended) {
                        Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(16.dp))
                    } else {
                        // Simple dot for non-recommended selected state, or stick to check for consistency
                        // The HTML design shows a small dot for basic, check for standard.
                        // Let's use dot for simplicity unless it's the recommended one which has a check in design
                        Box(Modifier.size(10.dp).clip(CircleShape).background(Color.White))
                    }
                } else if (isRecommended) {
                    // Recommended state usually shows Check icon in HTML design provided
                    Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
        }

        // Price
        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(price, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = LoginPrimary)
            Text("/mo", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray, modifier = Modifier.padding(bottom = 6.dp, start = 2.dp))
        }

        HorizontalDivider(color = if (isRecommended) LoginPrimary.copy(alpha = 0.2f) else Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp)

        // Features List
        Column(modifier = Modifier.padding(top = 12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            features.forEach { feature ->
                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        imageVector = feature.icon,
                        contentDescription = null,
                        tint = LoginPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = feature.text,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF374151), // gray-700
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}