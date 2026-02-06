package com.grunzimmer.app.mainui.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material.icons.rounded.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue // Fixes property delegate error
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.mainui.auth.viewmodel.AuthViewModel
import com.grunzimmer.app.presentation.theme.*
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.login_bg
import org.jetbrains.compose.resources.painterResource
// Correct Import for Koin 4.0+
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    // Injects the ViewModel
    val viewModel = koinViewModel<AuthViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.isOtpSent) {
        if (state.isOtpSent) {
            onLoginSuccess()
            viewModel.resetState()
        }
    }

    val isDark = isSystemInDarkTheme()

    // Theme Colors
    val backgroundColor = if (isDark) LoginBackgroundDark else LoginBackgroundLight
    val primaryColor = if (isDark) Color(0xFF34D399) else LoginPrimary
    val textColor = if (isDark) Color.White else LoginTextDark
    val subtitleColor = if (isDark) Color.Gray else Color(0xFF4a5950)
    val inputBgColor = if (isDark) Color(0xFF1c2e24) else Color.White
    val borderColor = if (isDark) Sage.copy(alpha = 0.3f) else Sage
    val prefixBgColor = if (isDark) Color.White.copy(alpha = 0.05f) else SageLight.copy(alpha = 0.3f)

    var phoneNumber by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // --- Background Layer: Subtle Botanical Art ---
        Image(
            painter = painterResource(Res.drawable.login_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(if (isDark) 0.05f else 0.1f),
            colorFilter = if (isDark) ColorFilter.tint(Color.White, blendMode = BlendMode.SrcIn) else null
        )

        // --- Content Layer ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // 1. Top Section: Logo & Welcome
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 48.dp)
            ) {
                // Logo Mark
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(primaryColor.copy(alpha = 0.1f))
                        .border(1.dp, Sage.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Spa,
                        contentDescription = "Logo",
                        tint = primaryColor,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Wordmark
                Text(
                    text = "GRÜNZIMMER",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    ),
                    color = if (isDark) Color.White else LoginPrimary
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Headings
                Text(
                    text = "Welcome to GrünZimmer",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 32.sp,
                        lineHeight = 36.sp
                    ),
                    color = primaryColor,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Your terrace oasis awaits. Enter your phone number to get started.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp
                    ),
                    color = subtitleColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.widthIn(max = 280.dp)
                )
            }

            // 2. Middle Section: Form
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Phone Input Group
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(inputBgColor)
                        .border(1.dp, borderColor, RoundedCornerShape(16.dp))
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Country Code Prefix
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .background(prefixBgColor)
                                .padding(horizontal = 20.dp)
                                .border(width = 0.dp, color = Color.Transparent),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "+91",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                ),
                                color = primaryColor
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Rounded.ExpandMore,
                                contentDescription = null,
                                tint = primaryColor,
                                modifier = Modifier.size(16.dp)
                            )

                            // Vertical Divider
                            Spacer(modifier = Modifier.width(12.dp))
                            Box(
                                modifier = Modifier
                                    .height(24.dp)
                                    .width(1.dp)
                                    .background(Sage.copy(alpha = 0.3f))
                            )
                        }

                        // Input Field
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (phoneNumber.isEmpty()) {
                                Text(
                                    text = "98765 43210",
                                    color = Color(0xFF8ba396),
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                            BasicTextField(
                                value = phoneNumber,
                                onValueChange = { if (it.length <= 10 && it.all { char -> char.isDigit() }) phoneNumber = it },
                                textStyle = MaterialTheme.typography.titleMedium.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = textColor
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                singleLine = true,
                                cursorBrush = SolidColor(primaryColor),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                if (state.error != null) {
                    Text(
                        text = state.error!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                // Primary Action Button
                Button(
                    onClick = {
                        if (phoneNumber.isNotEmpty()) {
                            viewModel.sendOtp("+91$phoneNumber")
                        }
                    },
                    enabled = !state.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LoginPrimary,
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 2.dp
                    )
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(
                            text = "Send OTP",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                letterSpacing = 0.5.sp
                            )
                        )
                    }
                }

                // Separator
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(modifier = Modifier.weight(1f).height(1.dp).background(Sage.copy(alpha = 0.4f)))
                    Text(
                        text = "OR",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        ),
                        color = Color.Gray
                    )
                    Box(modifier = Modifier.weight(1f).height(1.dp).background(Sage.copy(alpha = 0.4f)))
                }

                // Secondary Action: Google
                Button(
                    onClick = { /* TODO: Implement Google Auth */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(1.dp, Sage.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDark) Color(0xFF1c2e24) else Color.White,
                        contentColor = subtitleColor
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Text(
                        text = "G",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
                        color = primaryColor
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Continue with Google",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        color = subtitleColor
                    )
                }
            }

            // 3. Footer: Legal
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                val legalText = buildAnnotatedString {
                    append("By continuing, you agree to our ")
                    withStyle(style = SpanStyle(color = primaryColor, textDecoration = TextDecoration.Underline)) {
                        append("Terms of Service")
                    }
                    append(" and ")
                    withStyle(style = SpanStyle(color = primaryColor, textDecoration = TextDecoration.Underline)) {
                        append("Privacy Policy")
                    }
                    append(".")
                }

                Text(
                    text = legalText,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Home Indicator Handle
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(100))
                        .background(Color.Gray.copy(alpha = 0.3f))
                )
            }
        }
    }
}