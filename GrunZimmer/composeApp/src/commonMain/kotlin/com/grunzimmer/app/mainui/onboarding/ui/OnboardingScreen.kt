package com.grunzimmer.app.mainui.onboarding.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.BackgroundDark
import com.grunzimmer.app.presentation.theme.Onboarding1Bg
import com.grunzimmer.app.presentation.theme.Onboarding1Primary
import com.grunzimmer.app.presentation.theme.Onboarding2Bg
import com.grunzimmer.app.presentation.theme.Onboarding2Primary
import com.grunzimmer.app.presentation.theme.Onboarding3Bg
import com.grunzimmer.app.presentation.theme.Onboarding3Primary
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.onboarding_one
import grunzimmer.composeapp.generated.resources.onboarding_three
import grunzimmer.composeapp.generated.resources.onboarding_two
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class OnboardingPageData(
    val title: String,
    val description: String,
    val icon: ImageVector? = null,
    val image: DrawableResource? = null,
    val backgroundColor: Color,
    val primaryColor: Color
)

@Composable
fun OnboardingScreen(
    onOnboardingFinished: () -> Unit = {}
) {
    val isDark = isSystemInDarkTheme()
    val scope = rememberCoroutineScope()

    val pages = listOf(
        OnboardingPageData(
            title = "Turn Your Space\nGreen",
            description = "We design beautiful terrace and balcony gardens for urban homes.",
            image = Res.drawable.onboarding_one,
            backgroundColor = if (isDark) BackgroundDark else Onboarding1Bg,
            primaryColor = if (isDark) Color(0xFF4ade80) else Onboarding1Primary
        ),
        OnboardingPageData(
            title = "Experts Handle\nEverything",
            description = "From design to setup and maintenance, our team takes care of it all.",
            image = Res.drawable.onboarding_two,
            backgroundColor = if (isDark) BackgroundDark else Onboarding2Bg,
            primaryColor = if (isDark) Color(0xFF4ade80) else Onboarding2Primary
        ),
        OnboardingPageData(
            title = "Enjoy Green\nLiving",
            description = "Book garden services, track work, and maintain your green space with ease.",
            image = Res.drawable.onboarding_three,
            backgroundColor = if (isDark) BackgroundDark else Onboarding3Bg,
            primaryColor = if (isDark) Color(0xFF4ade80) else Onboarding3Primary
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val currentPage = pages[pagerState.currentPage]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(currentPage.backgroundColor)
    ) {
        // --- Top Section: Illustration ---
        Box(
            modifier = Modifier
                .weight(0.55f)
                .fillMaxWidth()
        ) {
            // Rounded Bottom Container for Image
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 40.dp,
                            bottomEnd = 40.dp
                        )
                    )
                    .background(if (isDark) Color.White.copy(alpha = 0.05f) else Color.White)
                    .shadow(elevation = 20.dp, spotColor = Color.Black.copy(alpha = 0.05f))
            ) {
                // If an image is provided, show it. Otherwise show Icon.
                if (pages[pagerState.currentPage].image != null) {
                    Image(
                        painter = painterResource(pages[pagerState.currentPage].image!!),
                        contentDescription = "Illustration",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    // Fallback for pages without images (Icons + Gradient)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        currentPage.primaryColor.copy(alpha = 0.1f),
                                        Color.Transparent
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        pages[pagerState.currentPage].icon?.let { icon ->
                            Icon(
                                imageVector = icon,
                                contentDescription = "Illustration",
                                modifier = Modifier.size(120.dp),
                                tint = currentPage.primaryColor.copy(alpha = 0.8f)
                            )
                        }
                    }
                }

                // Inner Shadow Overlay (Bottom Curve Depth)
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.05f))
                            )
                        )
                )
            }

            // Skip Button (Top Right)
            if (pagerState.currentPage < pages.lastIndex) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 48.dp, end = 24.dp)
                ) {
                    Text(
                        text = "Skip",
                        color = if (pages[pagerState.currentPage].image != null) Color.White else currentPage.primaryColor.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (pages[pagerState.currentPage].image != null) Color.Black.copy(alpha = 0.3f)
                                else Color.White.copy(alpha = 0.5f)
                            )
                            .clickable { onOnboardingFinished() }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }

        // --- Bottom Section: Content & Navigation ---
        Column(
            modifier = Modifier
                .weight(0.45f)
                .padding(horizontal = 32.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Pager Content
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = true,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = pages[page].title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            lineHeight = 40.sp,
                            letterSpacing = (-0.5).sp
                        ),
                        color = pages[page].primaryColor,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = pages[page].description,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            lineHeight = 24.sp
                        ),
                        color = if (isDark) Color.LightGray else Color(0xFF4A5952),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }

            // Footer Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Custom Pagination Indicators
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(pages.size) { index ->
                        val isSelected = pagerState.currentPage == index
                        val width = animateDpAsState(
                            targetValue = if (isSelected) 32.dp else 8.dp,
                            animationSpec = tween(durationMillis = 300),
                            label = "dotWidth"
                        )

                        Box(
                            modifier = Modifier
                                .height(8.dp)
                                .width(width.value)
                                .clip(CircleShape)
                                .background(
                                    if (isSelected) currentPage.primaryColor
                                    else currentPage.primaryColor.copy(alpha = 0.2f)
                                )
                        )
                    }
                }

                // Buttons
                Box {
                    // "Next" Button for Page 1 & 2
                    // FIX: Use fully qualified name to avoid ambiguity with RowScope extension
                    androidx.compose.animation.AnimatedVisibility(
                        visible = pagerState.currentPage < pages.lastIndex,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        if (pagerState.currentPage == 1) {
                            Text(
                                text = "Next",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = currentPage.primaryColor,
                                modifier = Modifier
                                    .clickable {
                                        scope.launch {
                                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                        }
                                    }
                                    .padding(8.dp)
                            )
                        }
                        else {
                            Row(
                                modifier = Modifier
                                    .height(48.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(currentPage.primaryColor)
                                    .clickable {
                                        scope.launch {
                                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                        }
                                    }
                                    .padding(horizontal = 24.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Next",
                                    color = Color.White,
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                Icon(
                                    imageVector = Icons.Rounded.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }

            // "Get Started" Full Width Button for Last Page
            if (pagerState.currentPage == pages.lastIndex) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(currentPage.primaryColor)
                        .shadow(
                            elevation = 10.dp,
                            spotColor = currentPage.primaryColor.copy(alpha = 0.25f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { onOnboardingFinished() },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Get Started",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                letterSpacing = 0.5.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Rounded.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(0.dp))
            }
        }
    }
}