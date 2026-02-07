package com.grunzimmer.app.mainui.booking.ui

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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.LoginPrimary

@Composable
fun ScheduleSiteVisitScreen(
    onBackClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    var selectedDate by remember { mutableStateOf(14) } // Default selected date
    var selectedTimeSlot by remember { mutableStateOf("Morning") }

    // Colors from HTML design
    val PrimaryColor = Color(0xFF1F6F43)
    val SageColor = Color(0xFFA8CBB7)
    val EarthColor = Color(0xFFEFE9D8)
    val BackgroundLight = Color(0xFFFDFCFB)
    val TextMain = Color(0xFF121714)
    val TextMuted = Color(0xFF6B7280) // Gray-500 equivalent

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            // Top App Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundLight.copy(alpha = 0.8f))
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
                        tint = PrimaryColor
                    )
                }
                Text(
                    text = "Schedule Site Visit",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(40.dp))
            }
        },
        bottomBar = {
            // Fixed Bottom Action Bar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundLight.copy(alpha = 0.95f))
                    .border(1.dp, Color(0xFFF3F4F6))
                    .padding(24.dp)
                    .padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = onConfirmClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(10.dp, spotColor = PrimaryColor.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text(
                        text = "Confirm Schedule",
                        fontSize = 16.sp,
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
        ) {
            // Progress Indicator
            Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("STEP 2 OF 3", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryColor.copy(alpha = 0.7f), letterSpacing = 0.5.sp)
                    Text("66%", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(50))
                        .background(EarthColor)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.66f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(50))
                            .background(PrimaryColor)
                    )
                }
            }

            // Calendar Section
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .shadow(2.dp, RoundedCornerShape(16.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFF3F4F6), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column {
                    // Month Navigation
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {}, modifier = Modifier.size(32.dp)) {
                            Icon(Icons.Default.ChevronLeft, null, tint = Color.Gray)
                        }
                        Text("October 2024", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextMain)
                        IconButton(onClick = {}, modifier = Modifier.size(32.dp)) {
                            Icon(Icons.Default.ChevronRight, null, tint = Color.Gray)
                        }
                    }

                    // Calendar Grid Header
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        listOf("S", "M", "T", "W", "T", "F", "S").forEach { day ->
                            Text(
                                text = day,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )
                        }
                    }

                    // Calendar Days (Mimicking HTML Layout)
                    // Rows of 7 days
                    // Week 1 (Partial, starts Tue)
                    CalendarRow {
                        EmptyDay(2) // Sun, Mon empty
                        DisabledDay("1")
                        DisabledDay("2")
                        DisabledDay("3")
                        AvailableDay("4", false, SageColor, PrimaryColor) {}
                        AvailableDay("5", false, SageColor, PrimaryColor) {}
                    }
                    // Week 2
                    CalendarRow {
                        (6..12).forEach { day ->
                            AvailableDay(day.toString(), false, SageColor, PrimaryColor) { selectedDate = day }
                        }
                    }
                    // Week 3
                    CalendarRow {
                        AvailableDay("13", false, SageColor, PrimaryColor) { selectedDate = 13 }
                        AvailableDay("14", selectedDate == 14, SageColor, PrimaryColor) { selectedDate = 14 }
                        (15..19).forEach { day ->
                            AvailableDay(day.toString(), selectedDate == day, SageColor, PrimaryColor) { selectedDate = day }
                        }
                    }
                    // Week 4
                    CalendarRow {
                        (20..26).forEach { day ->
                            AvailableDay(day.toString(), selectedDate == day, SageColor, PrimaryColor) { selectedDate = day }
                        }
                    }
                }
            }

            // Time Slot Section
            Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
                Text(
                    text = "Select Time Slot",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMain,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    TimeSlotCard(
                        title = "Morning",
                        time = "10:00 AM - 12:00 PM",
                        isSelected = selectedTimeSlot == "Morning",
                        primaryColor = PrimaryColor,
                        earthColor = EarthColor,
                        onClick = { selectedTimeSlot = "Morning" }
                    )
                    TimeSlotCard(
                        title = "Afternoon",
                        time = "03:00 PM - 05:00 PM",
                        isSelected = selectedTimeSlot == "Afternoon",
                        primaryColor = PrimaryColor,
                        earthColor = EarthColor,
                        onClick = { selectedTimeSlot = "Afternoon" }
                    )
                }
            }

            // Summary Info
            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .background(SageColor.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                    .border(1.dp, SageColor.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.Info, null, tint = PrimaryColor, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = "Our expert will visit your site on Oct ${selectedDate}th to assess your space and suggest the best urban gardening solutions.",
                        fontSize = 14.sp,
                        color = Color(0xFF4B5563),
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// --- Helper Components ---

@Composable
fun CalendarRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        content = content
    )
}

@Composable
fun RowScope.EmptyDay(count: Int) {
    repeat(count) {
        Spacer(modifier = Modifier.weight(1f).height(40.dp))
    }
}

@Composable
fun RowScope.DisabledDay(text: String) {
    Box(
        modifier = Modifier.weight(1f).height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.LightGray)
    }
}

@Composable
fun RowScope.AvailableDay(
    text: String,
    isSelected: Boolean,
    sageColor: Color,
    primaryColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(if (isSelected) primaryColor else sageColor.copy(alpha = 0.2f))
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else primaryColor
            )
        }
    }
}

@Composable
fun TimeSlotCard(
    title: String,
    time: String,
    isSelected: Boolean,
    primaryColor: Color,
    earthColor: Color,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) primaryColor else earthColor
    val textColor = if (isSelected) Color.White else Color(0xFF374151) // gray-700
    val subTextColor = if (isSelected) Color.White.copy(alpha = 0.8f) else Color(0xFF6B7280) // gray-500
    val icon = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked
    val iconColor = if (isSelected) Color.White else Color(0xFFD1D5DB) // gray-300

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = textColor)
            Text(time, fontSize = 12.sp, color = subTextColor)
        }
        Icon(icon, null, tint = iconColor, modifier = Modifier.size(24.dp))
    }
}