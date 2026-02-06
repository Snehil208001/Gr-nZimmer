package com.grunzimmer.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grunzimmer.app.mainui.auth.ui.LoginScreen
import com.grunzimmer.app.mainui.auth.ui.OtpVerificationScreen // Import this
import com.grunzimmer.app.mainui.onboarding.ui.OnboardingScreen
import com.grunzimmer.app.mainui.splash.ui.SplashScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route,
        modifier = modifier
    ) {
        // ... (Splash and Onboarding remain same)
        composable(route = Screens.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screens.Onboarding.route) {
                        popUpTo(Screens.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screens.Onboarding.route) {
            OnboardingScreen(
                onOnboardingFinished = {
                    navController.navigate(Screens.Login.route) {
                        popUpTo(Screens.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        // Login Screen: Navigate to OTP on success
        composable(route = Screens.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screens.OtpVerification.route)
                }
            )
        }

        // OTP Screen: Navigate to Home on success, Back to Login on back click
        composable(route = Screens.OtpVerification.route) {
            OtpVerificationScreen(
                onVerificationSuccess = {
                    // Navigate to Home (Placeholder for now)
                    // navController.navigate(Screens.Home.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}