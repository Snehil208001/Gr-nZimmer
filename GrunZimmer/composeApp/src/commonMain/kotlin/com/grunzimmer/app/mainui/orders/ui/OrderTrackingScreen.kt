package com.grunzimmer.app.mainui.orders.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.BackgroundLight
import com.grunzimmer.app.presentation.theme.LoginPrimary
import com.grunzimmer.app.presentation.theme.Sage
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.orderexpert
import grunzimmer.composeapp.generated.resources.ordertrackingscreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun OrderTrackingScreen(
    onBackClick: () -> Unit,
    onViewQuotationClick: () -> Unit, // <--- Added callback
    modifier: Modifier = Modifier
) {
    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Transparent)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = LoginPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = "Order Status",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = LoginPrimary,
                    letterSpacing = (-0.5).sp
                )
                Spacer(modifier = Modifier.width(48.dp))
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .border(1.dp, Color.Gray.copy(alpha = 0.1f))
                    .padding(20.dp)
            ) {
                Button(
                    onClick = onViewQuotationClick, // <--- Connected callback here
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(10.dp, spotColor = LoginPrimary.copy(alpha = 0.25f)),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LoginPrimary)
                ) {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "View Quotation",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                TextButton(
                    onClick = { /* Contact Support */ },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                ) {
                    Text(
                        text = "Need Help? Contact Support",
                        color = LoginPrimary.copy(alpha = 0.8f),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
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
            // Order Summary Card
            OrderSummaryCard()

            Spacer(modifier = Modifier.height(32.dp))

            // Vertical Progress Timeline
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                TimelineItem(
                    status = StepStatus.COMPLETED,
                    title = "Request Submitted",
                    date = "Oct 24, 10:00 AM",
                    isLast = false
                )
                TimelineItem(
                    status = StepStatus.COMPLETED,
                    title = "Expert Assigned",
                    date = "Oct 25, 02:30 PM",
                    expertName = "Rahul Verma",
                    expertRole = "Senior Botanist",
                    expertImage = Res.drawable.orderexpert,
                    isLast = false
                )
                TimelineItem(
                    status = StepStatus.ACTIVE,
                    title = "Site Visit Scheduled",
                    date = "Today, 4:00 PM",
                    icon = Icons.Default.Schedule,
                    badge = "Upcoming",
                    isLast = false
                )
                TimelineItem(
                    status = StepStatus.PENDING,
                    title = "Quotation Review",
                    date = "Pending",
                    icon = Icons.Default.ReceiptLong,
                    isLast = false
                )
                TimelineItem(
                    status = StepStatus.PENDING,
                    title = "Installation",
                    date = "Pending",
                    icon = Icons.Default.LocalFlorist,
                    isLast = true
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun OrderSummaryCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 20.dp,
                spotColor = LoginPrimary.copy(alpha = 0.08f),
                shape = RoundedCornerShape(12.dp)
            )
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.ordertrackingscreen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "ORDER #GZ-8821",
                color = Sage,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            Text(
                text = "Terrace Garden Setup",
                color = Color(0xFF121714),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Kankarbagh, Patna",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}

enum class StepStatus { COMPLETED, ACTIVE, PENDING }

@Composable
fun TimelineItem(
    status: StepStatus,
    title: String,
    date: String,
    isLast: Boolean,
    icon: ImageVector = Icons.Default.Check,
    expertName: String? = null,
    expertRole: String? = null,
    expertImage: DrawableResource? = null,
    badge: String? = null
) {
    Row(
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        // Timeline Line Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(32.dp)
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        when (status) {
                            StepStatus.COMPLETED -> Sage.copy(alpha = 0.2f)
                            StepStatus.ACTIVE -> LoginPrimary
                            StepStatus.PENDING -> BackgroundLight
                        }
                    )
                    .then(
                        if (status == StepStatus.PENDING) Modifier.border(2.dp, Color.LightGray, CircleShape)
                        else if (status == StepStatus.ACTIVE) Modifier.shadow(4.dp, CircleShape, spotColor = LoginPrimary)
                        else Modifier
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = when (status) {
                        StepStatus.COMPLETED -> LoginPrimary
                        StepStatus.ACTIVE -> Color.White
                        StepStatus.PENDING -> Color.LightGray
                    },
                    modifier = Modifier.size(18.dp)
                )
            }

            // Line
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .padding(vertical = 4.dp)
                        .background(
                            when (status) {
                                StepStatus.COMPLETED -> Brush.verticalGradient(listOf(Sage, LoginPrimary))
                                else -> SolidColor(Color.LightGray.copy(alpha = 0.5f))
                            }
                        )
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Content Column
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = if (isLast) 0.dp else 32.dp)
                .then(if (status == StepStatus.PENDING) Modifier.padding(top = 4.dp) else Modifier)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (status == StepStatus.PENDING) Color.Gray else Color(0xFF121714)
            )
            Text(
                text = date,
                fontSize = 14.sp,
                color = if (status == StepStatus.ACTIVE) LoginPrimary else Color.Gray,
                fontWeight = if (status == StepStatus.ACTIVE) FontWeight.Medium else FontWeight.Normal,
                modifier = Modifier.padding(top = 2.dp)
            )

            // Optional Badge
            if (badge != null) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(LoginPrimary.copy(alpha = 0.1f))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = badge,
                        color = LoginPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Expert Profile
            if (expertName != null) {
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Expert Image (with Fallback)
                    if (expertImage != null) {
                        Image(
                            painter = painterResource(expertImage),
                            contentDescription = expertName,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Sage)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = expertName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF121714)
                        )
                        Text(
                            text = expertRole ?: "",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}