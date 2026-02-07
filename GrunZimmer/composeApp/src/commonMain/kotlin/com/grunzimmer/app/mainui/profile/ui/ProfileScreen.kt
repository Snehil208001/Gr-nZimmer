package com.grunzimmer.app.mainui.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Deck
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Yard
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.grunzimmer.app.presentation.theme.BackgroundLight
import com.grunzimmer.app.presentation.theme.LoginPrimary

@Composable
fun ProfileScreen(
    onNavigateToAddress: () -> Unit = {},
    onNavigateToPropertyType: () -> Unit = {},
    onNavigateToMaintenancePlans: () -> Unit = {}, // <--- Added
    onNavigateToOrders: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    val isDark = isSystemInDarkTheme()
    val backgroundColor = if (isDark) Color(0xFF131F18) else BackgroundLight
    val surfaceColor = if (isDark) Color(0xFF1C2E26) else Color.White
    val textColor = if (isDark) Color.White else Color(0xFF121714)
    val mutedTextColor = if (isDark) Color(0xFF8BA396) else Color(0xFF688274)
    val dividerColor = if (isDark) Color.White.copy(alpha = 0.1f) else Color.Gray.copy(alpha = 0.1f)

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Profile",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    letterSpacing = (-0.5).sp
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // User Profile Section
            Box(modifier = Modifier.padding(bottom = 16.dp)) {
                Box(
                    modifier = Modifier
                        .size(112.dp)
                        .shadow(10.dp, CircleShape, spotColor = LoginPrimary.copy(alpha = 0.2f))
                        .clip(CircleShape)
                        .background(LoginPrimary)
                        .border(4.dp, surfaceColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "AS",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(surfaceColor)
                        .border(1.dp, backgroundColor, CircleShape)
                        .clickable { /* Edit Profile */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = LoginPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Text(
                text = "Ananya Singh",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Text(
                text = "+91 98765 43210",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = mutedTextColor,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Settings Groups
            SettingsGroup(
                title = "Account",
                items = listOf(
                    SettingsItemData(
                        icon = Icons.Default.PinDrop,
                        title = "Address details",
                        subtitle = "Manage delivery locations",
                        onClick = onNavigateToAddress
                    ),
                    SettingsItemData(
                        icon = Icons.Default.Deck,
                        title = "Property type",
                        subtitle = "Terrace & Balcony details",
                        onClick = onNavigateToPropertyType
                    )
                ),
                surfaceColor = surfaceColor,
                dividerColor = dividerColor,
                textColor = textColor,
                mutedTextColor = mutedTextColor
            )

            SettingsGroup(
                title = "Orders & Services",
                items = listOf(
                    SettingsItemData(
                        icon = Icons.Outlined.Inventory2,
                        title = "My orders",
                        onClick = onNavigateToOrders
                    ),
                    SettingsItemData(
                        icon = Icons.Default.Yard,
                        title = "Maintenance plans",
                        onClick = onNavigateToMaintenancePlans // <--- Connected callback
                    )
                ),
                surfaceColor = surfaceColor,
                dividerColor = dividerColor,
                textColor = textColor,
                mutedTextColor = mutedTextColor
            )

            SettingsGroup(
                title = "Support",
                items = listOf(
                    SettingsItemData(
                        icon = Icons.AutoMirrored.Filled.Help,
                        title = "Help & support",
                        onClick = {}
                    ),
                    SettingsItemData(
                        icon = Icons.Default.ChatBubble,
                        title = "Contact us",
                        onClick = {}
                    )
                ),
                surfaceColor = surfaceColor,
                dividerColor = dividerColor,
                textColor = textColor,
                mutedTextColor = mutedTextColor
            )

            SettingsGroup(
                title = "Legal",
                items = listOf(
                    SettingsItemData(
                        icon = Icons.Default.Description,
                        title = "Terms & conditions",
                        onClick = {}
                    ),
                    SettingsItemData(
                        icon = Icons.Default.Lock,
                        title = "Privacy policy",
                        onClick = {}
                    )
                ),
                surfaceColor = surfaceColor,
                dividerColor = dividerColor,
                textColor = textColor,
                mutedTextColor = mutedTextColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Logout Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLogout() }
                    .border(1.dp, LoginPrimary, RoundedCornerShape(12.dp))
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = null,
                        tint = LoginPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Log out",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = LoginPrimary
                    )
                }
            }

            Text(
                text = "Version 2.4.1 • GrünZimmer",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
            )
        }
    }
}

data class SettingsItemData(
    val icon: ImageVector,
    val title: String,
    val subtitle: String? = null,
    val onClick: () -> Unit
)

@Composable
fun SettingsGroup(
    title: String,
    items: List<SettingsItemData>,
    surfaceColor: Color,
    dividerColor: Color,
    textColor: Color,
    mutedTextColor: Color
) {
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        Text(
            text = title.uppercase(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = mutedTextColor,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(surfaceColor)
                .border(1.dp, dividerColor, RoundedCornerShape(12.dp))
        ) {
            items.forEachIndexed { index, item ->
                SettingsItem(
                    item = item,
                    textColor = textColor,
                    mutedTextColor = mutedTextColor,
                    dividerColor = dividerColor,
                    showDivider = index < items.size - 1
                )
            }
        }
    }
}

@Composable
fun SettingsItem(
    item: SettingsItemData,
    textColor: Color,
    mutedTextColor: Color,
    dividerColor: Color,
    showDivider: Boolean
) {
    Column(modifier = Modifier.clickable { item.onClick() }) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(LoginPrimary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = LoginPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )
                if (item.subtitle != null) {
                    Text(
                        text = item.subtitle,
                        fontSize = 12.sp,
                        color = mutedTextColor
                    )
                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
        if (showDivider) {
            HorizontalDivider(
                color = dividerColor,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}