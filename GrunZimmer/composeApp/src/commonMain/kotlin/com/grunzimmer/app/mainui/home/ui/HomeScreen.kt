package com.grunzimmer.app.mainui.home.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.mainui.orders.ui.OrderTrackingScreen
import com.grunzimmer.app.mainui.profile.ui.ProfileScreen
import com.grunzimmer.app.mainui.services.ui.ServiceListingScreen
import com.grunzimmer.app.presentation.theme.PrimaryGreen
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.home_hero
import grunzimmer.composeapp.generated.resources.inspiration_1
import grunzimmer.composeapp.generated.resources.inspiration_2
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

enum class HomeTab {
    HOME, SERVICES, ORDERS, PROFILE
}

@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToServiceDetail: (String) -> Unit // New callback
) {
    val isDark = isSystemInDarkTheme()
    var currentTab by remember { mutableStateOf(HomeTab.HOME) }

    // Design System Colors
    val primaryColor = PrimaryGreen
    val bgLight = Color(0xFFFDFBF7)
    val bgDark = Color(0xFF131F18)
    val surfaceLight = Color.White
    val surfaceDark = Color(0xFF1E2A23)

    val backgroundColor = if (isDark) bgDark else bgLight
    val surfaceColor = if (isDark) surfaceDark else surfaceLight
    val borderColor = if (isDark) Color.White.copy(alpha = 0.1f) else Color.Gray.copy(alpha = 0.1f)

    Scaffold(
        containerColor = backgroundColor,
        bottomBar = {
            HomeBottomNavigation(
                isDark = isDark,
                surfaceColor = surfaceColor,
                borderColor = borderColor,
                primaryColor = primaryColor,
                currentTab = currentTab,
                onTabSelected = { newTab -> currentTab = newTab }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (currentTab) {
                HomeTab.HOME -> {
                    HomeContent(
                        isDark = isDark,
                        primaryColor = primaryColor,
                        surfaceColor = surfaceColor,
                        borderColor = borderColor,
                        onNavigateToServices = { currentTab = HomeTab.SERVICES },
                        onNavigateToOrders = { currentTab = HomeTab.ORDERS }
                    )
                }
                HomeTab.SERVICES -> {
                    ServiceListingScreen(
                        onBackClick = { currentTab = HomeTab.HOME },
                        onServiceClick = { serviceId ->
                            onNavigateToServiceDetail(serviceId) // Navigate to Detail
                        }
                    )
                }
                HomeTab.ORDERS -> {
                    OrderTrackingScreen(
                        onBackClick = { currentTab = HomeTab.HOME }
                    )
                }
                HomeTab.PROFILE -> {
                    ProfileScreen(
                        onNavigateToAddress = { /* Navigate to Address Edit */ },
                        onNavigateToOrders = { currentTab = HomeTab.ORDERS },
                        onLogout = { /* Handle Logout Logic */ }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    isDark: Boolean,
    primaryColor: Color,
    surfaceColor: Color,
    borderColor: Color,
    onNavigateToServices: () -> Unit,
    onNavigateToOrders: () -> Unit
) {
    val scrollState = rememberScrollState()
    val primaryLight = Color(0xFFA8CBB7)
    val textMain = if (isDark) Color.White else Color(0xFF121714)
    val textMuted = Color(0xFF688274)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // 1. Header Section
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = null,
                        tint = if (isDark) primaryLight else primaryColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Patna",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.5.sp
                        ),
                        color = if (isDark) primaryLight else primaryColor
                    )
                }
                Text(
                    text = "Good Morning,\nAnanya",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 32.sp
                    ),
                    color = textMain
                )
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(surfaceColor)
                    .border(1.dp, borderColor, CircleShape)
                    .clickable { /* Handle Notifications */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications",
                    tint = if (isDark) Color.White else primaryColor
                )
            }
        }

        // 2. Hero Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
                .clip(RoundedCornerShape(24.dp))
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(24.dp), spotColor = primaryColor.copy(alpha = 0.1f))
        ) {
            Image(
                painter = painterResource(Res.drawable.home_hero),
                contentDescription = "Garden Hero",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                            startY = 300f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp)
            ) {
                Text(
                    text = "Bring Nature to Your Home",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Transform your terrace or balcony into a lush green sanctuary.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFE0E0E0)
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = onNavigateToServices,
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text("Request Garden Setup", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Rounded.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 3. Ongoing Orders Widget
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp, start = 4.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Active Order", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = textMain)
            Text(
                text = "In Progress",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = if (isDark) primaryLight else primaryColor,
                modifier = Modifier
                    .background(if (isDark) primaryColor.copy(alpha = 0.3f) else primaryColor.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = surfaceColor),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, borderColor),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(16.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                .clickable { onNavigateToOrders() }
        ) {
            Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(primaryColor.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Outlined.LocalFlorist, contentDescription = null, tint = primaryColor)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Balcony Revamp", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = textMain)
                        Text("#2045", style = MaterialTheme.typography.bodySmall, color = textMuted)
                    }
                    Text(
                        "Site visit scheduled for tomorrow, 10 AM.",
                        style = MaterialTheme.typography.bodySmall,
                        color = textMuted,
                        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(100))
                            .background(if (isDark) Color.Gray.copy(alpha = 0.3f) else Color(0xFFF3F4F6))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.66f)
                                .fillMaxHeight()
                                .background(primaryColor)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 4. Services Section
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp, start = 4.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Our Services", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = textMain)
            Text(
                text = "See All",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = if (isDark) primaryLight else primaryColor,
                modifier = Modifier.clickable { onNavigateToServices() }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ServiceItem(
                icon = Icons.Outlined.Deck,
                title = "Terrace\nGarden",
                subtitle = "Rooftop oasis",
                primaryColor = primaryColor,
                isDark = isDark,
                onClick = onNavigateToServices
            )
            ServiceItem(
                icon = Icons.Outlined.Balcony,
                title = "Balcony\nGarden",
                subtitle = "Cozy corners",
                primaryColor = primaryColor,
                isDark = isDark,
                onClick = onNavigateToServices
            )
            ServiceItem(
                icon = Icons.Outlined.GridView,
                title = "Vertical\nGarden",
                subtitle = "Space saver",
                primaryColor = primaryColor,
                isDark = isDark,
                onClick = onNavigateToServices
            )
            ServiceItem(
                icon = Icons.Outlined.Yard,
                title = "Garden\nCare",
                subtitle = "Maintenance",
                primaryColor = primaryColor,
                isDark = isDark,
                onClick = onNavigateToServices
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 5. Inspiration Section
        Text(
            text = "Inspiration for You",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = textMain,
            modifier = Modifier.padding(bottom = 16.dp, start = 4.dp)
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            InspirationCard(
                modifier = Modifier.weight(1f),
                title = "Succulent Mix",
                image = Res.drawable.inspiration_1
            )
            InspirationCard(
                modifier = Modifier.weight(1f),
                title = "Indoor Vibes",
                image = Res.drawable.inspiration_2
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ServiceItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    primaryColor: Color,
    isDark: Boolean,
    onClick: () -> Unit
) {
    val bg = if (isDark) primaryColor.copy(alpha = 0.15f) else Color(0xFFA8CBB7).copy(alpha = 0.3f)

    Column(
        modifier = Modifier
            .width(80.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(bg)
                .padding(12.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(if (isDark) primaryColor.copy(alpha = 0.3f) else Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isDark) Color.White else primaryColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp
            ),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray,
            maxLines = 1
        )
    }
}

@Composable
fun InspirationCard(
    modifier: Modifier = Modifier,
    title: String,
    image: DrawableResource
) {
    Box(
        modifier = modifier
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))))
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomStart).padding(12.dp)
        )
    }
}

@Composable
fun HomeBottomNavigation(
    isDark: Boolean,
    surfaceColor: Color,
    borderColor: Color,
    primaryColor: Color,
    currentTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit
) {
    Surface(
        color = surfaceColor,
        border = BorderStroke(1.dp, borderColor),
        shadowElevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                icon = Icons.Rounded.Home,
                label = "Home",
                isSelected = currentTab == HomeTab.HOME,
                selectedColor = if (isDark) Color(0xFFA8CBB7) else primaryColor,
                unselectedColor = Color.Gray,
                onClick = { onTabSelected(HomeTab.HOME) }
            )
            BottomNavItem(
                icon = Icons.Rounded.LocalFlorist,
                label = "Services",
                isSelected = currentTab == HomeTab.SERVICES,
                selectedColor = primaryColor,
                unselectedColor = Color.Gray,
                onClick = { onTabSelected(HomeTab.SERVICES) }
            )
            BottomNavItem(
                icon = Icons.Rounded.ReceiptLong,
                label = "Orders",
                isSelected = currentTab == HomeTab.ORDERS,
                selectedColor = primaryColor,
                unselectedColor = Color.Gray,
                onClick = { onTabSelected(HomeTab.ORDERS) }
            )
            BottomNavItem(
                icon = Icons.Rounded.Person,
                label = "Profile",
                isSelected = currentTab == HomeTab.PROFILE,
                selectedColor = primaryColor,
                unselectedColor = Color.Gray,
                onClick = { onTabSelected(HomeTab.PROFILE) }
            )
        }
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }.padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) selectedColor else unselectedColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            ),
            color = if (isSelected) selectedColor else unselectedColor
        )
    }
}