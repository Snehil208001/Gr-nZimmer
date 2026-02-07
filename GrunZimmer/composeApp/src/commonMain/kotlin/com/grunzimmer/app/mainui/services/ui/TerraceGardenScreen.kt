package com.grunzimmer.app.mainui.services.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border // <--- ADDED THIS IMPORT
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.BackgroundLight
import com.grunzimmer.app.presentation.theme.LoginPrimary
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.servicedetail
import org.jetbrains.compose.resources.painterResource

@Composable
fun TerraceGardenScreen(
    onBackClick: () -> Unit,
    onScheduleVisitClick: () -> Unit
) {
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.clip(CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = LoginPrimary
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Terrace Garden Setup",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF121714)
                )
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(48.dp))
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
                    .shadow(elevation = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Payments, null, tint = LoginPrimary, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("STARTING FROM ₹25,000", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = LoginPrimary)
                }
                Button(
                    onClick = onScheduleVisitClick,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LoginPrimary)
                ) {
                    Text("Request Site Visit", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, null)
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .aspectRatio(4f / 3f)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(Res.drawable.servicedetail),
                    contentDescription = "Terrace Garden",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

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
                    text = "We handle everything from soil preparation to custom seating for your perfect urban oasis.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF688274),
                    lineHeight = 24.sp
                )
            }

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text("What's Included", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF121714), modifier = Modifier.padding(bottom = 16.dp))

                val includedItems = listOf(
                    SimpleIncludedItem(Icons.Default.DesignServices, "Design Consultation"),
                    SimpleIncludedItem(Icons.Default.LocalFlorist, "Plant Selection"),
                    SimpleIncludedItem(Icons.Default.Agriculture, "Soil Preparation"),
                    SimpleIncludedItem(Icons.Default.MenuBook, "Maintenance Guide")
                )

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        SimpleIncludedItemCard(includedItems[0], Modifier.weight(1f))
                        SimpleIncludedItemCard(includedItems[1], Modifier.weight(1f))
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        SimpleIncludedItemCard(includedItems[2], Modifier.weight(1f))
                        SimpleIncludedItemCard(includedItems[3], Modifier.weight(1f))
                    }
                }
            }

            Column(modifier = Modifier.padding(20.dp)) {
                Text("Your Terrace Details", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF121714), modifier = Modifier.padding(vertical = 16.dp))

                FormLabel("Area size")
                OutlinedTextField(
                    value = areaSize,
                    onValueChange = { areaSize = it },
                    placeholder = { Text("e.g. 500") },
                    trailingIcon = { Text("sq. ft.", color = Color.Gray, modifier = Modifier.padding(end = 16.dp)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFDDE4E0),
                        focusedBorderColor = LoginPrimary
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(20.dp))
                FormLabel("Sunlight")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    SelectableChip("Low", selectedSunlight == "Low") { selectedSunlight = "Low" }
                    SelectableChip("Medium", selectedSunlight == "Medium") { selectedSunlight = "Medium" }
                    SelectableChip("High", selectedSunlight == "High") { selectedSunlight = "High" }
                }

                Spacer(modifier = Modifier.height(20.dp))
                FormLabel("Budget range")
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = selectedBudget,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { Icon(Icons.Default.ArrowDropDown, null, Modifier.clickable { budgetExpanded = true }) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                    DropdownMenu(
                        expanded = budgetExpanded,
                        onDismissRequest = { budgetExpanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(text = { Text("₹25,000 - ₹50,000") }, onClick = { selectedBudget = "₹25,000 - ₹50,000"; budgetExpanded = false })
                        DropdownMenuItem(text = { Text("₹50,000 - ₹1,00,000") }, onClick = { selectedBudget = "₹50,000 - ₹1,00,000"; budgetExpanded = false })
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}