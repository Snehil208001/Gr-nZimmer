package com.grunzimmer.app.mainui.booking.ui

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
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocalFlorist
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.inspiration_2
import org.jetbrains.compose.resources.painterResource

@Composable
fun BookingSuccessScreen(
    onGoToDashboard: () -> Unit,
    onClose: () -> Unit
) {
    // Colors matching HTML
    val PrimaryColor = Color(0xFF1F6F43)
    val BackgroundLight = Color(0xFFF6F8F7)
    val TextMain = Color(0xFF121714)
    val SurfaceWhite = Color.White
    val BorderColor = Color(0xFFF3F4F6)

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundLight.copy(alpha = 0.9f))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Close Button
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable(onClick = onClose)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Close, "Close", tint = TextMain)
                }

                Text(
                    text = "Booking Confirmed",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMain
                )

                // Help Button (Text style)
                Text(
                    text = "Help",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { /* Help Action */ }
                )
            }
        },
        bottomBar = {
            // Footer Actions
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundLight)
                    .padding(24.dp)
                    .padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = onGoToDashboard,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(10.dp, spotColor = PrimaryColor.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text("Go to Dashboard", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { /* Add to Calendar */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(2.dp, PrimaryColor, RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Icon(Icons.Default.CalendarToday, null, tint = PrimaryColor, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Add to Calendar", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Success Icon & Title
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(PrimaryColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Site Visit Scheduled!",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = PrimaryColor,
                textAlign = TextAlign.Center,
                lineHeight = 36.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Our expert will visit your space on Oct 15th at 10:00 AM. Get ready to turn your space green!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = TextMain.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Illustration
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(Res.drawable.inspiration_2), // Using existing resource
                    contentDescription = "Success",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.verticalGradient(listOf(PrimaryColor.copy(alpha = 0.05f), Color.Transparent)))
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Summary Card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 12.dp, spotColor = Color.Black.copy(alpha = 0.05f), shape = RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceWhite)
                    .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
                    .padding(20.dp)
            ) {
                // Service Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(PrimaryColor.copy(alpha = 0.1f))
                                .padding(8.dp)
                        ) {
                            Icon(Icons.Outlined.LocalFlorist, null, tint = PrimaryColor, modifier = Modifier.size(20.dp))
                        }
                        Spacer(Modifier.width(12.dp))
                        Text("Service", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                    }
                    Text("Terrace Garden Setup", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextMain)
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = BorderColor)

                // Location Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(PrimaryColor.copy(alpha = 0.1f))
                                .padding(8.dp)
                        ) {
                            Icon(Icons.Default.LocationOn, null, tint = PrimaryColor, modifier = Modifier.size(20.dp))
                        }
                        Spacer(Modifier.width(12.dp))
                        Text("Location", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                    }
                    Text("Patna, Bihar", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextMain)
                }
            }

            // Bottom Spacing
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}