package com.grunzimmer.app.mainui.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.mainui.auth.viewmodel.AuthViewModel
import com.grunzimmer.app.presentation.theme.*
import grunzimmer.composeapp.generated.resources.Res
import grunzimmer.composeapp.generated.resources.login_bg
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
// Correct Import for Koin 4.0+
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun OtpVerificationScreen(
    onVerificationSuccess: () -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel = koinViewModel<AuthViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.isVerified) {
        if (state.isVerified) {
            onVerificationSuccess()
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

    var otpValue by remember { mutableStateOf("") }
    val otpLength = 6

    // Timer State
    var timeLeft by remember { mutableStateOf(25) }
    var isTimerRunning by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isTimerRunning, key2 = timeLeft) {
        if (isTimerRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else if (timeLeft == 0) {
            isTimerRunning = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // --- Background Layer ---
        Image(
            painter = painterResource(Res.drawable.login_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(if (isDark) 0.05f else 0.03f),
            colorFilter = if (isDark) ColorFilter.tint(Color.White, blendMode = BlendMode.SrcIn) else null
        )

        // --- Header / Navigation ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Back Button
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onBackClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = primaryColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Brand Title
            Text(
                text = "GRÜNZIMMER",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ),
                color = primaryColor
            )

            // Spacer for balance
            Spacer(modifier = Modifier.size(40.dp))
        }

        // --- Main Content ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text Content
            Text(
                text = "Verify Your Number",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 28.sp
                ),
                color = if (isDark) Color.White else primaryColor,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            val subtitle = buildAnnotatedString {
                append("Enter the 6-digit code sent to your phone\n")
            }

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = subtitleColor,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // OTP Input Fields
            BasicTextField(
                value = otpValue,
                onValueChange = { if (it.length <= otpLength && it.all { char -> char.isDigit() }) otpValue = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                decorationBox = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(otpLength) { index ->
                            val char = if (index < otpValue.length) otpValue[index].toString() else ""
                            val isFocused = index == otpValue.length

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(0.85f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(inputBgColor)
                                    .border(
                                        width = if (isFocused || char.isNotEmpty()) 2.dp else 1.dp,
                                        color = if (isFocused || char.isNotEmpty()) primaryColor else borderColor,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = char,
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 24.sp
                                    ),
                                    color = if (isDark) Color.White else primaryColor
                                )
                            }
                        }
                    }
                }
            )

            if (state.error != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = state.error!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Action Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Verify Button
                Button(
                    onClick = {
                        if (otpValue.length == otpLength) {
                            viewModel.verifyOtp(otpValue)
                        }
                    },
                    enabled = !state.isLoading && otpValue.length == otpLength,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
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
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Verify & Continue",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.Rounded.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                // Resend Section
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val timerText = "00:${if (timeLeft < 10) "0$timeLeft" else timeLeft}"

                    Text(
                        text = buildAnnotatedString {
                            append("Resend code in ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = if (isDark) Color.White else primaryColor)) {
                                append(timerText)
                            }
                        },
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = subtitleColor
                    )

                    Text(
                        text = "Resend OTP",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = if (timeLeft == 0) primaryColor else primaryColor.copy(alpha = 0.4f),
                        modifier = Modifier
                            .clickable(enabled = timeLeft == 0) {
                                timeLeft = 25
                                isTimerRunning = true
                                // TODO: Trigger resend logic in ViewModel
                            }
                    )
                }
            }

            // Native Home Indicator Placeholder
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .width(134.dp)
                    .height(5.dp)
                    .clip(RoundedCornerShape(100))
                    .background(Color.Black.copy(alpha = 0.2f))
            )
        }
    }
}