package com.grunzimmer.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grunzimmer.app.mainui.auth.ui.LoginScreen
import com.grunzimmer.app.mainui.auth.ui.OtpVerificationScreen
import com.grunzimmer.app.mainui.home.ui.HomeScreen
import com.grunzimmer.app.mainui.onboarding.ui.OnboardingScreen
import com.grunzimmer.app.mainui.profile.ui.ProfileSetupScreen
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
        // Splash Screen
        composable(route = Screens.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screens.Onboarding.route) {
                        popUpTo(Screens.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Onboarding Screen
        composable(route = Screens.Onboarding.route) {
            OnboardingScreen(
                onOnboardingFinished = {
                    navController.navigate(Screens.Login.route) {
                        popUpTo(Screens.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        // Login Screen
        composable(route = Screens.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    // Navigate to OTP for phone auth
                    navController.navigate(Screens.OtpVerification.route)
                },
                onGoogleLoginSuccess = {
                    // Navigate directly to Profile/Home for Google auth (Skipping OTP)
                    navController.navigate(Screens.ProfileSetup.route) {
                        popUpTo(Screens.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // OTP Verification Screen
        composable(route = Screens.OtpVerification.route) {
            OtpVerificationScreen(
                onVerificationSuccess = {
                    navController.navigate(Screens.ProfileSetup.route) {
                        popUpTo(Screens.Login.route) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Profile Setup Screen
        composable(route = Screens.ProfileSetup.route) {
            ProfileSetupScreen(
                onSaveAndContinue = {
                    navController.navigate(Screens.Home.route) {
                        popUpTo(Screens.ProfileSetup.route) { inclusive = true }
                    }
                },
                onSkip = {
                    navController.navigate(Screens.Home.route)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // Home Screen
        composable(route = Screens.Home.route) {
            HomeScreen(
                onNavigateToServices = {
                    // Placeholder: Add route for service listing if available
                    // navController.navigate(Screens.ServiceListing.route)
                    println("Navigate to Services")
                },
                onNavigateToOrders = {
                    println("Navigate to Orders")
                },
                onNavigateToProfile = {
                    // For now, navigating back to setup as a profile view placeholder
                    navController.navigate(Screens.ProfileSetup.route)
                }
            )
        }
    }
}