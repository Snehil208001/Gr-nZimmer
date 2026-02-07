package com.grunzimmer.app.mainui.services.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.LoginPrimary

@Composable
fun GardenMaintenanceScreen(
    onBackClick: () -> Unit,
    onRescheduleClick: () -> Unit,
    onGetHelpClick: () -> Unit
) {
    // Custom colors from HTML design
    val PrimaryColor = Color(0xFF1F6F43)
    val SecondaryColor = Color(0xFFE0E8E3) // Soft Sage
    val AccentColor = Color(0xFFF2F7F4)
    val BackgroundLight = Color(0xFFFDFBF7) // Warm Off-White
    val TextMain = Color(0xFF052011)
    val TextMuted = Color(0xFF526E5F)

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            // Top App Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryColor)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .statusBarsPadding(), // Handle status bar inset
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
                        tint = Color.White.copy(alpha = 0.9f)
                    )
                }
                Text(
                    text = "Garden Maintenance",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                IconButton(
                    onClick = onGetHelpClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Help,
                        contentDescription = "Help",
                        tint = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        },
        bottomBar = {
            // Sticky Bottom CTA
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .shadow(elevation = 16.dp, spotColor = Color.Black.copy(alpha = 0.1f))
                    .border(1.dp, Color(0xFFF3F4F6))
                    .padding(16.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Reschedule Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(SecondaryColor)
                            .clickable(onClick = onRescheduleClick)
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.History, null, tint = PrimaryColor, modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Reschedule", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
                        }
                    }

                    // Get Help Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(PrimaryColor)
                            .clickable(onClick = onGetHelpClick)
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.SupportAgent, null, tint = Color.White, modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Get Help", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
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

            // Active Plan Card
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 24.dp)
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(16.dp), spotColor = PrimaryColor.copy(alpha = 0.08f))
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(1.dp, SecondaryColor.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            ) {
                Column {
                    // Decorative Top Border
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .background(Brush.horizontalGradient(listOf(PrimaryColor, Color(0xFF2D8558))))
                    )

                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Column {
                                // Active Badge
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFFE8F5E9), RoundedCornerShape(50))
                                        .border(1.dp, PrimaryColor.copy(alpha = 0.1f), RoundedCornerShape(50))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(6.dp)
                                                .clip(CircleShape)
                                                .background(PrimaryColor)
                                        )
                                        Spacer(Modifier.width(6.dp))
                                        Text("Active Plan", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
                                    }
                                }
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = "Monthly\nGarden Care",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = TextMain,
                                    lineHeight = 28.sp
                                )
                            }
                            // Icon Circle
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(SecondaryColor),
                                contentAlignment = Alignment.Center
                            ) {
                                // CHANGED: Icons.Default.PottedPlant -> Icons.Default.LocalFlorist
                                Icon(Icons.Default.LocalFlorist, null, tint = PrimaryColor, modifier = Modifier.size(28.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Details Row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(BackgroundLight, RoundedCornerShape(8.dp))
                                .border(1.dp, SecondaryColor.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(PrimaryColor.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Icon(Icons.Default.CalendarMonth, null, tint = PrimaryColor, modifier = Modifier.size(20.dp))
                            }
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text("NEXT VISIT", fontSize = 10.sp, fontWeight = FontWeight.Medium, color = TextMuted, letterSpacing = 0.5.sp)
                                Text("15th Oct, 2023", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextMain)
                            }
                            Spacer(Modifier.weight(1f))
                            Column(horizontalAlignment = Alignment.End) {
                                Text("FREQUENCY", fontSize = 10.sp, fontWeight = FontWeight.Medium, color = TextMuted, letterSpacing = 0.5.sp)
                                Text("Monthly", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextMain)
                            }
                        }
                    }

                    // Card Footer
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(AccentColor)
                            .border(1.dp, SecondaryColor.copy(alpha = 0.3f))
                            .padding(horizontal = 20.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Verified, null, tint = TextMuted, modifier = Modifier.size(14.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Plan ID: #GZ-8921", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextMuted)
                        }
                        Text(
                            text = "Manage Plan",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor,
                            modifier = Modifier.clickable { /* Manage */ }
                        )
                    }
                }
            }

            // Upcoming Tasks
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Upcoming Tasks", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextMain)
                    Text(
                        text = "Visit 15th Oct",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextMuted,
                        modifier = Modifier
                            .background(SecondaryColor.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    TaskCard("Pruning", "Trimming dead leaves & shaping.", Icons.Default.ContentCut, PrimaryColor, TextMain, TextMuted, Modifier.weight(1f))
                    // CHANGED: Icons.Default.Compost -> Icons.Default.Agriculture
                    TaskCard("Fertilizing", "Organic nutrient boost.", Icons.Default.Agriculture, PrimaryColor, TextMain, TextMuted, Modifier.weight(1f))
                }

                Spacer(Modifier.height(12.dp))

                // Full Width Task Card
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .border(1.dp, SecondaryColor.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF1F8F4)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.MonitorHeart, null, tint = PrimaryColor, modifier = Modifier.size(20.dp))
                    }
                    Spacer(Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Health Check", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextMain)
                        Text("Comprehensive pest & disease inspection.", fontSize = 12.sp, color = TextMuted)
                    }
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .border(2.dp, SecondaryColor, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.ChevronRight, null, tint = SecondaryColor, modifier = Modifier.size(16.dp))
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            // Past Visits
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text("Past Visits", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextMain, modifier = Modifier.padding(bottom = 12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .border(1.dp, SecondaryColor.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                ) {
                    PastVisitItem("Monthly Maintenance", "10th Sept • Completed by Rahul", true)
                    HorizontalDivider(thickness = 1.dp, color = Color(0xFFF3F4F6))
                    PastVisitItem("Monthly Maintenance", "12th Aug • Completed by Rahul", false)
                }
            }

            // Illustration Placeholder Area
            Spacer(Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(Brush.verticalGradient(listOf(Color(0xFFE8F5E9), Color.Transparent))),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "GrünZimmer: Bringing nature to your urban home",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryColor.copy(alpha = 0.6f),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Padding for sticky bottom bar overlap
            Spacer(Modifier.height(24.dp))
        }
    }
}

// --- Helper Components ---

@Composable
fun TaskCard(title: String, subtitle: String, icon: ImageVector, primary: Color, textMain: Color, textMuted: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFE0E8E3).copy(alpha = 0.4f), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFF1F8F4)),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = primary, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.height(8.dp))
        Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = textMain)
        Text(subtitle, fontSize = 12.sp, color = textMuted, lineHeight = 16.sp)
    }
}

@Composable
fun PastVisitItem(title: String, subtitle: String, isFirst: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0xFFDCFCE7)), // Green-100
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Check, null, tint = Color(0xFF15803D), modifier = Modifier.size(18.dp)) // Green-700
        }
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF052011))
            Text(subtitle, fontSize = 12.sp, color = Color(0xFF526E5F))
        }
        Icon(Icons.Default.Description, null, tint = Color(0xFF526E5F), modifier = Modifier.size(20.dp))
    }
}