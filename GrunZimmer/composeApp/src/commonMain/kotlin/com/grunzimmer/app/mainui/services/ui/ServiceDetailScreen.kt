package com.grunzimmer.app.mainui.services.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.mainui.services.viewmodel.ServiceDetailViewModel
import com.grunzimmer.app.presentation.theme.BackgroundLight
import com.grunzimmer.app.presentation.theme.LoginPrimary
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.inspiration_2
import grunzimmer.composeapp.generated.resources.servicedetail
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ServiceDetailScreen(
    serviceId: String,
    onBackClick: () -> Unit,
    onScheduleVisitClick: () -> Unit
) {
    val viewModel = koinViewModel<ServiceDetailViewModel>()

    // UI State for form inputs (local state for this screen)
    var areaSize by remember { mutableStateOf("") }
    var selectedSunlight by remember { mutableStateOf("Medium") }
    var budgetExpanded by remember { mutableStateOf(false) }
    var selectedBudget by remember { mutableStateOf("₹25,000 - ₹50,000") }

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
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
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
                    text = "Terrace Garden Setup",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF121714)
                )
                Spacer(modifier = Modifier.size(40.dp)) // Spacer for optical centering
            }
        },
        bottomBar = {
            // Sticky Footer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
                    .padding(bottom = 16.dp) // Extra padding for safe area if needed
                    .shadow(elevation = 20.dp, spotColor = Color.Black.copy(alpha = 0.05f))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Payments,
                        contentDescription = null,
                        tint = LoginPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "STARTING FROM ₹25,000",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = LoginPrimary,
                        letterSpacing = 0.5.sp
                    )
                }
                Button(
                    onClick = onScheduleVisitClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(10.dp, spotColor = LoginPrimary.copy(alpha = 0.3f)),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LoginPrimary)
                ) {
                    Text(
                        text = "Request Site Visit",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
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
                    .padding(horizontal = 16.dp)
                    .aspectRatio(4f / 3f) // Matches aspect-[4/3]
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(Res.drawable.servicedetail),
                    contentDescription = "Terrace Garden",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Gradient Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.1f))))
                )
            }

            // 2. Service Description
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Transform your empty roof into a lush sanctuary",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF121714),
                    lineHeight = 30.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "We handle everything from soil preparation to custom seating for your perfect urban oasis. Enjoy a stress-free setup.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF688274),
                    lineHeight = 24.sp
                )
            }

            // 3. What's Included
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "What's Included",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF121714),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Grid Layout manually constructed with Rows/Columns
                val includedItems = listOf(
                    IncludedItem(Icons.Default.DesignServices, "Design Consultation"),
                    IncludedItem(Icons.Default.LocalFlorist, "Plant Selection"), // Fixed Icon
                    IncludedItem(Icons.Default.Agriculture, "Soil Preparation"),
                    IncludedItem(Icons.Default.MenuBook, "Maintenance Guide")
                )

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        IncludedItemCard(includedItems[0], Modifier.weight(1f))
                        IncludedItemCard(includedItems[1], Modifier.weight(1f))
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        IncludedItemCard(includedItems[2], Modifier.weight(1f))
                        IncludedItemCard(includedItems[3], Modifier.weight(1f))
                    }
                }
            }

            // 4. Form
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Your Terrace Details",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF121714),
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                )

                // Area Size
                FormLabel("Area size")
                OutlinedTextField(
                    value = areaSize,
                    onValueChange = { areaSize = it },
                    placeholder = { Text("e.g. 500") },
                    trailingIcon = { Text("sq. ft.", color = Color.Gray, modifier = Modifier.padding(end = 16.dp)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedBorderColor = Color(0xFFDDE4E0),
                        focusedBorderColor = LoginPrimary
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Sunlight
                FormLabel("Sunlight")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    SelectableChip("Low", selectedSunlight == "Low") { selectedSunlight = "Low" }
                    SelectableChip("Medium", selectedSunlight == "Medium") { selectedSunlight = "Medium" }
                    SelectableChip("High", selectedSunlight == "High") { selectedSunlight = "High" }
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Budget Range
                FormLabel("Budget range")
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = selectedBudget,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                modifier = Modifier.clickable { budgetExpanded = true }
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedBorderColor = Color(0xFFDDE4E0),
                            focusedBorderColor = LoginPrimary
                        )
                    )
                    DropdownMenu(
                        expanded = budgetExpanded,
                        onDismissRequest = { budgetExpanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(text = { Text("₹25,000 - ₹50,000") }, onClick = { selectedBudget = "₹25,000 - ₹50,000"; budgetExpanded = false })
                        DropdownMenuItem(text = { Text("₹50,000 - ₹1,00,000") }, onClick = { selectedBudget = "₹50,000 - ₹1,00,000"; budgetExpanded = false })
                        DropdownMenuItem(text = { Text("₹1,00,000+") }, onClick = { selectedBudget = "₹1,00,000+"; budgetExpanded = false })
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Upload Photos
                FormLabel("Terrace Photos")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color(0xFFDDE4E0), RoundedCornerShape(12.dp))
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .clickable { /* Handle Upload */ }
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(LoginPrimary.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.AddAPhoto, null, tint = LoginPrimary)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Upload from Gallery", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = LoginPrimary)
                        Text("Optional", fontSize = 12.sp, color = Color.Gray)
                    }
                }

                // Add padding at the bottom so content isn't hidden behind sticky footer
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun FormLabel(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF121714),
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
fun RowScope.SelectableChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) LoginPrimary.copy(alpha = 0.1f) else Color.White)
            .border(
                1.dp,
                if (isSelected) LoginPrimary else Color(0xFFDDE4E0),
                RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) LoginPrimary else Color(0xFF688274)
        )
    }
}

data class IncludedItem(val icon: ImageVector, val title: String)

@Composable
fun IncludedItemCard(item: IncludedItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFDDE4E0), RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            tint = LoginPrimary,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = item.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF121714),
            lineHeight = 18.sp
        )
    }
}