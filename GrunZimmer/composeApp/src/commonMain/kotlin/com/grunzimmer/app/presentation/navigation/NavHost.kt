// composeApp/src/commonMain/kotlin/com/grunzimmer/app/presentation/navigation/NavHost.kt
package com.grunzimmer.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grunzimmer.app.mainui.auth.ui.LoginScreen
import com.grunzimmer.app.mainui.auth.ui.OtpVerificationScreen
import com.grunzimmer.app.mainui.onboarding.ui.OnboardingScreen
import com.grunzimmer.app.mainui.profile.ui.ProfileSetupScreen // Import new screen
import com.grunzimmer.app.mainui.splash.ui.SplashScreen
// import com.grunzimmer.app.mainui.home.ui.HomeScreen // Use this when Home is ready

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
        // ... (Splash and Onboarding remain unchanged) ...
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

        composable(route = Screens.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screens.OtpVerification.route)
                }
            )
        }

        // Update OTP Screen to navigate to Profile Setup
        composable(route = Screens.OtpVerification.route) {
            OtpVerificationScreen(
                onVerificationSuccess = {
                    navController.navigate(Screens.ProfileSetup.route) {
                        // Optional: Clear back stack so user can't go back to OTP
                        popUpTo(Screens.Login.route) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Add Profile Setup Route
        composable(route = Screens.ProfileSetup.route) {
            ProfileSetupScreen(
                onSaveAndContinue = {
                    // Navigate to Home
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

        // Placeholder for Home
        composable(route = Screens.Home.route) {
            // HomeScreen() // Uncomment when HomeScreen is available
            androidx.compose.material3.Text("Home Screen Placeholder")
        }
        // ... previous code inside NavHost ...

        // Home Route (Updated)
        composable(route = Screens.Home.route) {
            com.grunzimmer.app.mainui.home.ui.HomeScreen(
                onNavigateToServices = {
                    // Navigate to Service Listing
                    // Assuming ServiceListingScreen is mapped to a route, e.g., "service_listing"
                    // If not defined in Screens.kt yet, you might need to add it.
                    // For now, based on your files, I'll assume you might use a specific route or placeholder
                    // navController.navigate(Screens.ServiceListing.route)
                    // Since I cannot see Screens.ServiceListing, I will log or placeholder:
                    println("Navigate to Services")
                },
                onNavigateToOrders = {
                    println("Navigate to Orders")
                },
                onNavigateToProfile = {
                    // Navigate to Profile View (Not Setup)
                    // If you have a ProfileView screen, navigate there.
                    // If you want to go back to Setup for demo:
                    navController.navigate(Screens.ProfileSetup.route)
                }
            )
        }
    }
}