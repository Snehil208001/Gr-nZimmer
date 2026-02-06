package com.grunzimmer.app.mainui.profile.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.mainui.splash.ui.alpha
import com.grunzimmer.app.presentation.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSetupScreen(
    onSaveAndContinue: () -> Unit,
    onSkip: () -> Unit,
    onBack: () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val scrollState = rememberScrollState()

    // Colors derived from design
    val primaryColor = PrimaryGreen
    val backgroundColor = if (isDark) BackgroundDark else Color(0xFFFAF9F6) // Warm White
    val sageColor = Sage
    val earthColor = if (isDark) Color(0xFF2C2C2C) else Color(0xFFEFE9D8)
    val inputBg = if (isDark) Color.White.copy(alpha = 0.05f) else Color.White
    val borderColor = if (isDark) Color.White.copy(alpha = 0.1f) else sageColor

    // Form State
    var fullName by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("Patna") }
    var address by remember { mutableStateOf("") }
    var areaSize by remember { mutableStateOf("") }
    var selectedPropertyType by remember { mutableStateOf("Terrace") }
    var selectedSunlight by remember { mutableStateOf<String?>(null) }
    var isCityDropdownExpanded by remember { mutableStateOf(false) }

    val cities = listOf("Patna", "Delhi", "Mumbai", "Bangalore")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // --- Background Decorations (Custom SVGs from HTML) ---
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Top Right Decoration
            val scale = 1.1f
            translate(left = size.width - 100f, top = -50f) {
                scale(scale) {
                    val path = Path().apply {
                        // Simplified path for the blob shape
                        moveTo(45.7f, -76.3f)
                        cubicTo(58.9f, -69.3f, 69.1f, -58.3f, 77.3f, -46.3f)
                        cubicTo(85.5f, -34.3f, 91.7f, -21.3f, 90.4f, -8.8f)
                        cubicTo(89.1f, 3.8f, 80.3f, 15.9f, 71.2f, 27.1f)
                        cubicTo(62.1f, 38.3f, 52.7f, 48.6f, 41.9f, 56.1f)
                        cubicTo(31.1f, 63.6f, 18.9f, 68.3f, 6.3f, 69.5f)
                        cubicTo(-6.3f, 70.7f, -19.3f, 68.4f, -31.4f, 61.9f)
                        close()
                    }
                    drawPath(
                        path = path,
                        color = primaryColor.copy(alpha = if (isDark) 0.05f else 0.1f),
                        style = Fill
                    )
                }
            }
        }

        // --- Main Content ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 88.dp) // Space for sticky footer
                .verticalScroll(scrollState)
        ) {
            // 1. Top Navigation
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            if (isDark) Color.White.copy(alpha = 0.1f) else Color.White.copy(alpha = 0.8f),
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Back",
                        tint = if (isDark) Color.White else primaryColor
                    )
                }

                TextButton(onClick = onSkip) {
                    Text(
                        text = "Skip",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = if (isDark) sageColor else primaryColor.copy(alpha = 0.7f)
                    )
                }
            }

            // 2. Header
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = "Set Up Your Profile",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 32.sp
                    ),
                    color = if (isDark) Color.White else primaryColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Help us design the perfect garden for your space.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = if (isDark) sageColor else primaryColor.copy(alpha = 0.8f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Form Section
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Personal Details
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    // Full Name
                    ProfileInputItem(label = "Full Name") {
                        OutlinedTextField(
                            value = fullName,
                            onValueChange = { fullName = it },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = inputBg,
                                unfocusedContainerColor = inputBg,
                                focusedIndicatorColor = primaryColor,
                                unfocusedIndicatorColor = borderColor,
                                focusedLabelColor = primaryColor,
                                unfocusedLabelColor = sageColor
                            ),
                            placeholder = { Text("e.g. Aditi Sharma", color = sageColor) }
                        )
                    }

                    // Phone Number (Disabled)
                    ProfileInputItem(label = "Phone Number", isAlpha = true) {
                        OutlinedTextField(
                            value = "+91 98765 43210",
                            onValueChange = {},
                            enabled = false,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            trailingIcon = {
                                Icon(Icons.Rounded.Lock, contentDescription = null, tint = sageColor)
                            },
                            colors = TextFieldDefaults.colors(
                                disabledContainerColor = if (isDark) Color.White.copy(alpha = 0.05f) else Color(0xFFF9FAFB),
                                disabledTextColor = if (isDark) Color.Gray else Color.Gray,
                                disabledIndicatorColor = borderColor.copy(alpha = 0.5f)
                            )
                        )
                    }
                }

                HorizontalDivider(color = sageColor.copy(alpha = 0.2f))

                // Address Details
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    // City Dropdown
                    ProfileInputItem(label = "City") {
                        ExposedDropdownMenuBox(
                            expanded = isCityDropdownExpanded,
                            onExpandedChange = { isCityDropdownExpanded = !isCityDropdownExpanded }
                        ) {
                            OutlinedTextField(
                                value = city,
                                onValueChange = {},
                                readOnly = true,
                                modifier = Modifier.fillMaxWidth().menuAnchor(),
                                shape = RoundedCornerShape(12.dp),
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCityDropdownExpanded) },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = inputBg,
                                    unfocusedContainerColor = inputBg,
                                    focusedIndicatorColor = primaryColor,
                                    unfocusedIndicatorColor = borderColor
                                )
                            )
                            ExposedDropdownMenu(
                                expanded = isCityDropdownExpanded,
                                onDismissRequest = { isCityDropdownExpanded = false },
                                modifier = Modifier.background(inputBg)
                            ) {
                                cities.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption) },
                                        onClick = {
                                            city = selectionOption
                                            isCityDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Address
                    ProfileInputItem(label = "Address") {
                        OutlinedTextField(
                            value = address,
                            onValueChange = { address = it },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3,
                            maxLines = 3,
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = inputBg,
                                unfocusedContainerColor = inputBg,
                                focusedIndicatorColor = primaryColor,
                                unfocusedIndicatorColor = borderColor
                            ),
                            placeholder = { Text("Street, Landmark, Pincode", color = sageColor) }
                        )
                    }
                }

                HorizontalDivider(color = sageColor.copy(alpha = 0.2f))

                // Garden Specifics
                Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    // Property Type
                    Column {
                        Text(
                            text = "Property Type",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = if (isDark) Color.LightGray else primaryColor,
                            modifier = Modifier.padding(start = 4.dp, bottom = 12.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            PropertyTypeChip(
                                label = "Terrace",
                                icon = Icons.Rounded.Deck,
                                selected = selectedPropertyType == "Terrace",
                                onClick = { selectedPropertyType = "Terrace" },
                                primaryColor = primaryColor,
                                unselectedColor = earthColor
                            )
                            PropertyTypeChip(
                                label = "Balcony",
                                icon = Icons.Rounded.Balcony,
                                selected = selectedPropertyType == "Balcony",
                                onClick = { selectedPropertyType = "Balcony" },
                                primaryColor = primaryColor,
                                unselectedColor = earthColor
                            )
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        // Area Size
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Area Size",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = if (isDark) Color.LightGray else primaryColor,
                                modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
                            )
                            OutlinedTextField(
                                value = areaSize,
                                onValueChange = { areaSize = it },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                trailingIcon = {
                                    Text("sq. ft.", style = MaterialTheme.typography.bodySmall, color = sageColor, modifier = Modifier.padding(end = 8.dp))
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = inputBg,
                                    unfocusedContainerColor = inputBg,
                                    focusedIndicatorColor = primaryColor,
                                    unfocusedIndicatorColor = borderColor
                                ),
                                placeholder = { Text("0", color = sageColor) }
                            )
                        }

                        // Sunlight
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Sunlight (Optional)",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                color = if (isDark) Color.LightGray else primaryColor,
                                modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                listOf("Low", "Med", "High").forEach { level ->
                                    val isSelected = selectedSunlight == level
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(56.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(
                                                if (isSelected) earthColor else inputBg
                                            )
                                            .border(
                                                1.dp,
                                                if (isSelected) primaryColor.copy(alpha = 0.2f) else borderColor.copy(alpha = 0.5f),
                                                RoundedCornerShape(8.dp)
                                            )
                                            .clickable { selectedSunlight = level },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = level,
                                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                                            color = if (isSelected) primaryColor else sageColor
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // 4. Sticky Footer
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    (if (isDark) BackgroundDark else Color(0xFFFAF9F6)).copy(alpha = 0.9f)
                )
                .border(
                    width = 1.dp,
                    color = sageColor.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp) // Only top border logic via modifier order if needed, but simple border fine
                )
                .padding(16.dp)
        ) {
            Button(
                onClick = onSaveAndContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ) {
                Text(
                    text = "Save & Continue",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Rounded.ArrowForward, contentDescription = null, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
fun ProfileInputItem(
    label: String,
    isAlpha: Boolean = false,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.then(if (isAlpha) Modifier.alpha(0.9f) else Modifier)) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = if (isSystemInDarkTheme()) Color.LightGray else PrimaryGreen,
            modifier = Modifier.padding(start = 4.dp, bottom = 6.dp)
        )
        content()
    }
}

@Composable
fun PropertyTypeChip(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    primaryColor: Color,
    unselectedColor: Color
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) primaryColor else unselectedColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) Color.White else primaryColor,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = if (selected) Color.White else primaryColor
        )
    }
}