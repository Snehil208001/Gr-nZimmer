package com.grunzimmer.app.mainui.booking.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.LoginPrimary
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.inspiration_2
import org.jetbrains.compose.resources.painterResource

@Composable
fun QuotationReviewScreen(
    onBackClick: () -> Unit,
    onApproveClick: () -> Unit,
    onRequestChangesClick: () -> Unit
) {
    // Custom Colors
    val PrimaryColor = Color(0xFF1F6F43)
    val BackgroundLight = Color(0xFFF6F8F7)
    val WarmWhite = Color(0xFFFAFAF8)
    val WarmBeige = Color(0xFFEFE9D8)
    val TextMain = Color(0xFF121714)
    val TextMuted = Color(0xFF6B7280) // gray-500

    // Dashed border stroke
    val dashedStroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    Scaffold(
        containerColor = BackgroundLight,
        topBar = {
            // App Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryColor)
                    .statusBarsPadding() // Handle top inset
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
                        tint = Color.White
                    )
                }
                Text(
                    text = "Quotation Details",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(1f),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.size(40.dp))
            }
        },
        bottomBar = {
            // Fixed Action Area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.9f))
                    .border(1.dp, Color(0xFFF3F4F6))
                    .padding(16.dp)
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.VerifiedUser,
                        contentDescription = null,
                        tint = Color(0xFF16A34A), // green-600
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "No payment required until work starts",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextMuted
                    )
                }

                Button(
                    onClick = onApproveClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(10.dp, spotColor = Color(0xFF14532D).copy(alpha = 0.2f)), // green-900 shadow
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text("Approve Quotation", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(20.dp))
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onRequestChangesClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .border(1.dp, PrimaryColor.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(
                        text = "Request Changes",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryColor
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
        ) {
            // Project Summary Card
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(192.dp) // h-48
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(Res.drawable.inspiration_2), // Using placeholder
                    contentDescription = "Project Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Gradient Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.4f), Color.Black.copy(alpha = 0.8f))))
                )

                // Content
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(50))
                            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text("Q-2023-892", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Terrace Garden Setup",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.SquareFoot, null, tint = Color(0xFFE5E7EB), modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("500 sq. ft. Area", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFFE5E7EB)) // gray-200
                        Spacer(Modifier.width(8.dp))
                        Box(Modifier.size(4.dp).background(Color.Gray, CircleShape))
                        Spacer(Modifier.width(8.dp))
                        Text("Oct 24, 2023", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFFE5E7EB))
                    }
                }
            }

            // Cost Breakdown
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .border(1.dp, Color(0xFFF3F4F6), RoundedCornerShape(16.dp))
                        .padding(20.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)) {
                        Icon(Icons.Default.ReceiptLong, null, tint = PrimaryColor, modifier = Modifier.size(24.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Cost Breakdown", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
                    }

                    // Items
                    // CHANGED: Icons.Default.PottedPlant -> Icons.Default.LocalFlorist
                    CostItem("Materials", "Plants, Pots, Soil Mix", Icons.Default.LocalFlorist, "₹45,000", PrimaryColor, TextMain, TextMuted)
                    HorizontalDivider(modifier = Modifier.padding(start = 44.dp), color = Color(0xFFF3F4F6))

                    CostItem("Labor Costs", "3 Days, 2 Workers", Icons.Default.Engineering, "₹12,000", PrimaryColor, TextMain, TextMuted)
                    HorizontalDivider(modifier = Modifier.padding(start = 44.dp), color = Color(0xFFF3F4F6))

                    CostItem("Design & Setup", "Consultation & Layout", Icons.Default.DesignServices, "₹8,000", PrimaryColor, TextMain, TextMuted)
                    HorizontalDivider(modifier = Modifier.padding(start = 44.dp), color = Color(0xFFF3F4F6))

                    // Maintenance (Free)
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier.size(32.dp).background(Color(0xFFF0FDF4), CircleShape), // green-50
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.WaterDrop, null, tint = PrimaryColor, modifier = Modifier.size(18.dp))
                            }
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text("Maintenance", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextMain)
                                Text("1st Month Support", fontSize = 12.sp, color = TextMuted)
                            }
                        }
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFDCFCE7), RoundedCornerShape(4.dp)) // green-100
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text("Free", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
                        }
                    }

                    // Total Divider
                    HorizontalDivider(
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                        thickness = 1.dp,
                        color = Color(0xFFE5E7EB)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text("Total Amount (Incl. GST)", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = TextMuted)
                        Text("₹65,000", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
                    }
                }
            }

            // Expert Notes
            Box(modifier = Modifier.padding(16.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(WarmBeige)
                        .border(1.dp, Color(0xFFE7E5E4), RoundedCornerShape(12.dp)) // stone-200
                        .padding(20.dp)
                ) {
                    // Decorative Icon (Watermark style)
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null,
                        tint = Color(0xFFD6D3D1).copy(alpha = 0.2f), // stone-300/20
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = 16.dp, y = (-16).dp)
                    )

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 8.dp)) {
                            Icon(Icons.Default.Psychology, null, tint = Color(0xFF292524), modifier = Modifier.size(18.dp)) // stone-800
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "EXPERT NOTES",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF292524),
                                letterSpacing = 1.sp
                            )
                        }
                        Text(
                            text = "This quotation includes complete soil preparation with organic compost, premium ceramic pots chosen for durability, and the installation of an automated drip irrigation system to ensure easy maintenance.",
                            fontSize = 14.sp,
                            color = Color(0xFF44403C), // stone-700
                            lineHeight = 22.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun CostItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    price: String,
    primaryColor: Color,
    textMain: Color,
    textMuted: Color
) {
    Row(
        modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(32.dp).background(Color(0xFFF0FDF4), CircleShape), // green-50
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = primaryColor, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text(title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = textMain)
                Text(subtitle, fontSize = 12.sp, color = textMuted)
            }
        }
        Text(price, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = textMain)
    }
}