package com.grunzimmer.app.mainui.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.Window
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.LoginPrimary

@Composable
fun AddressDetailsScreen(
    onBackClick: () -> Unit,
    onSaveAddressClick: () -> Unit
) {
    // UI State
    var locality by remember { mutableStateOf("") }
    var fullAddress by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    var floorLevel by remember { mutableStateOf("") }
    var approxArea by remember { mutableStateOf("") }

    var selectedPropertyType by remember { mutableStateOf("Terrace") }
    var hasLift by remember { mutableStateOf(true) }
    var selectedSunlight by remember { mutableStateOf("Partial") }

    // Colors
    val PrimaryColor = Color(0xFF1F6F43)
    val BackgroundLight = Color(0xFFF9F9F7)
    val SurfaceColor = Color.White
    val TextMain = Color(0xFF121714)
    val TextMuted = Color(0xFF688274)
    val BorderColor = Color(0xFFE5E7EB)

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
                    modifier = Modifier
                        .size(48.dp)
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
                    text = "Address Details",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    modifier = Modifier.weight(1f),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.size(48.dp)) // Balance back button
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundLight.copy(alpha = 0.9f))
                    .padding(16.dp)
                    .padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = onSaveAddressClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(10.dp, spotColor = PrimaryColor.copy(alpha = 0.3f)),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text("Save Address", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(20.dp))
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
            // Section 1: Service Location
            SectionTitle("Service Location", Icons.Default.LocationOn, PrimaryColor)

            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).fillMaxWidth()) {
                // City (Disabled)
                InputLabel("City")
                OutlinedTextField(
                    value = "Patna",
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledContainerColor = Color(0xFFF3F4F6),
                        disabledBorderColor = Color.Transparent,
                        disabledTextColor = Color.Gray
                    ),
                    trailingIcon = { Icon(Icons.Default.Lock, null, tint = Color.Gray) }
                )
                Spacer(Modifier.height(16.dp))

                // Locality
                InputLabel("Locality / Area")
                StyledTextField(value = locality, onValueChange = { locality = it }, placeholder = "e.g. Kankarbagh")
                Spacer(Modifier.height(16.dp))

                // Full Address
                InputLabel("Full Address")
                StyledTextField(
                    value = fullAddress,
                    onValueChange = { fullAddress = it },
                    placeholder = "House no, Building, Street",
                    singleLine = false,
                    minLines = 3
                )
                Spacer(Modifier.height(16.dp))

                // Pincode
                InputLabel("Pincode")
                StyledTextField(
                    value = pincode,
                    onValueChange = { pincode = it },
                    placeholder = "e.g. 800020",
                    keyboardType = KeyboardType.Number
                )
            }

            HorizontalDivider(color = BorderColor, modifier = Modifier.padding(vertical = 8.dp))

            // Section 2: Property Details
            SectionTitle("Property Details", Icons.Default.HomeWork, PrimaryColor)

            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).fillMaxWidth()) {
                InputLabel("Property Type", bottomPadding = 12.dp)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    PropertyTypeChip("Terrace", Icons.Default.Landscape, selectedPropertyType == "Terrace", PrimaryColor) { selectedPropertyType = "Terrace" }
                    PropertyTypeChip("Balcony", Icons.Default.Window, selectedPropertyType == "Balcony", PrimaryColor) { selectedPropertyType = "Balcony" }
                }

                Spacer(Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    // Floor Level
                    Column(modifier = Modifier.weight(1f)) {
                        InputLabel("Floor Level")
                        StyledTextField(
                            value = floorLevel,
                            onValueChange = { floorLevel = it },
                            placeholder = "0",
                            leadingIcon = { Icon(Icons.Default.Layers, null, tint = PrimaryColor) },
                            keyboardType = KeyboardType.Number
                        )
                    }

                    // Lift Availability
                    Column(modifier = Modifier.weight(1f)) {
                        InputLabel("Lift Availability")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .background(Color(0xFFF3F4F6), RoundedCornerShape(12.dp))
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            SelectionToggle("Yes", hasLift) { hasLift = true }
                            SelectionToggle("No", !hasLift) { hasLift = false }
                        }
                    }
                }
            }

            HorizontalDivider(color = BorderColor, modifier = Modifier.padding(vertical = 8.dp))

            // Section 3: Space Details
            SectionTitle("Space Details", Icons.Default.LocalFlorist, PrimaryColor)

            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).fillMaxWidth()) {
                InputLabel("Sunlight Availability", bottomPadding = 12.dp)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    val weightMod = Modifier.weight(1f)
                    SunlightCard("Low", Icons.Default.Cloud, selectedSunlight == "Low", weightMod) { selectedSunlight = "Low" }
                    SunlightCard("Partial", Icons.Default.WbSunny, selectedSunlight == "Partial", weightMod) { selectedSunlight = "Partial" }
                    SunlightCard("Full Sun", Icons.Default.WbSunny, selectedSunlight == "Full Sun", weightMod) { selectedSunlight = "Full Sun" }
                }

                Spacer(Modifier.height(16.dp))

                InputLabel("Approximate Area")
                StyledTextField(
                    value = approxArea,
                    onValueChange = { approxArea = it },
                    placeholder = "e.g. 500",
                    trailingIcon = {
                        Text(
                            "Sq ft",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextMuted,
                            modifier = Modifier.background(Color(0xFFF3F4F6), RoundedCornerShape(6.dp)).padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    },
                    keyboardType = KeyboardType.Number
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// --- Helper Components ---

@Composable
fun SectionTitle(text: String, icon: ImageVector, color: Color) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = color)
    }
}

@Composable
fun InputLabel(text: String, bottomPadding: androidx.compose.ui.unit.Dp = 8.dp) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF121714),
        modifier = Modifier.padding(start = 4.dp, bottom = bottomPadding)
    )
}

@Composable
fun StyledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    singleLine: Boolean = true,
    minLines: Int = 1,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color(0xFF688274)) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedBorderColor = Color(0xFFE5E7EB),
            focusedBorderColor = LoginPrimary,
            cursorColor = LoginPrimary
        ),
        singleLine = singleLine,
        minLines = minLines,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Composable
fun PropertyTypeChip(text: String, icon: ImageVector, isSelected: Boolean, primaryColor: Color, onClick: () -> Unit) {
    val bgColor = if (isSelected) primaryColor else Color.White
    val contentColor = if (isSelected) Color.White else Color(0xFF688274)
    val borderColor = if (isSelected) primaryColor else Color(0xFFE5E7EB)

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(50))
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = contentColor, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, fontSize = 14.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium, color = contentColor)
    }
}

@Composable
fun RowScope.SelectionToggle(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val bgColor = if (isSelected) Color.White else Color.Transparent
    val textColor = if (isSelected) LoginPrimary else Color(0xFF688274)
    val shadowElevation = if (isSelected) 1.dp else 0.dp

    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .shadow(shadowElevation, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = 14.sp, fontWeight = if(isSelected) FontWeight.Bold else FontWeight.Medium, color = textColor)
    }
}

@Composable
fun SunlightCard(text: String, icon: ImageVector, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val borderColor = if (isSelected) LoginPrimary else Color(0xFFE5E7EB)
    val bgAlpha = if (isSelected) 0.1f else 1f
    val bgColor = if (isSelected) LoginPrimary.copy(alpha = bgAlpha) else Color.White
    val iconColor = if (isSelected) LoginPrimary else Color(0xFF688274)

    Column(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(24.dp))
        Spacer(Modifier.height(4.dp))
        Text(text, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium, color = iconColor)
    }
}