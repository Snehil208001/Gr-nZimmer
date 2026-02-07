package com.grunzimmer.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.grunzimmer.app.mainui.auth.ui.LoginScreen
import com.grunzimmer.app.mainui.auth.ui.OtpVerificationScreen
import com.grunzimmer.app.mainui.booking.ui.BookingSuccessScreen
import com.grunzimmer.app.mainui.booking.ui.QuotationReviewScreen
import com.grunzimmer.app.mainui.booking.ui.ScheduleSiteVisitScreen
import com.grunzimmer.app.mainui.home.ui.HomeScreen
import com.grunzimmer.app.mainui.onboarding.ui.OnboardingScreen
import com.grunzimmer.app.mainui.profile.ui.AddressDetailsScreen
import com.grunzimmer.app.mainui.profile.ui.MaintenancePlansScreen
import com.grunzimmer.app.mainui.profile.ui.ProfileSetupScreen
import com.grunzimmer.app.mainui.profile.ui.PropertyTypeScreen
import com.grunzimmer.app.mainui.services.ui.BalconyGardenScreen
import com.grunzimmer.app.mainui.services.ui.GardenMaintenanceScreen
import com.grunzimmer.app.mainui.services.ui.KitchenGardenScreen
import com.grunzimmer.app.mainui.services.ui.ServiceDetailScreen
import com.grunzimmer.app.mainui.services.ui.TerraceGardenScreen
import com.grunzimmer.app.mainui.services.ui.VerticalGardenScreen
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
                    navController.navigate(Screens.OtpVerification.route)
                },
                onGoogleLoginSuccess = {
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
                onNavigateToProfile = {
                    navController.navigate(Screens.ProfileSetup.route)
                },
                onNavigateToServiceDetail = { serviceId ->
                    when (serviceId) {
                        "1" -> navController.navigate(Screens.TerraceGarden.route)
                        "2" -> navController.navigate(Screens.BalconyGarden.route)
                        "3" -> navController.navigate(Screens.VerticalGarden.route)
                        "4" -> navController.navigate(Screens.KitchenGarden.route)
                        "5" -> navController.navigate(Screens.GardenMaintenance.route)
                        else -> navController.navigate(Screens.ServiceDetail.createRoute(serviceId))
                    }
                },
                onNavigateToAddress = {
                    navController.navigate(Screens.AddressDetails.route)
                },
                onNavigateToPropertyType = {
                    navController.navigate(Screens.PropertyType.route)
                },
                onNavigateToMaintenancePlans = {
                    navController.navigate(Screens.MaintenancePlans.route)
                },
                onNavigateToQuotation = { // <--- Added logic
                    navController.navigate(Screens.QuotationReview.route)
                }
            )
        }

        // Service Screens
        composable(route = Screens.TerraceGarden.route) {
            TerraceGardenScreen(
                onBackClick = { navController.popBackStack() },
                onScheduleVisitClick = { navController.navigate(Screens.ScheduleSiteVisit.route) }
            )
        }
        composable(route = Screens.BalconyGarden.route) {
            BalconyGardenScreen(
                onBackClick = { navController.popBackStack() },
                onScheduleVisitClick = { navController.navigate(Screens.ScheduleSiteVisit.route) }
            )
        }
        composable(route = Screens.VerticalGarden.route) {
            VerticalGardenScreen(
                onBackClick = { navController.popBackStack() },
                onScheduleVisitClick = { navController.navigate(Screens.ScheduleSiteVisit.route) }
            )
        }
        composable(route = Screens.KitchenGarden.route) {
            KitchenGardenScreen(
                onBackClick = { navController.popBackStack() },
                onScheduleVisitClick = { navController.navigate(Screens.ScheduleSiteVisit.route) }
            )
        }
        composable(route = Screens.GardenMaintenance.route) {
            GardenMaintenanceScreen(
                onBackClick = { navController.popBackStack() },
                onRescheduleClick = { /* Handle Reschedule */ },
                onGetHelpClick = { /* Handle Help */ }
            )
        }

        // Profile Detail Screens
        composable(route = Screens.AddressDetails.route) {
            AddressDetailsScreen(
                onBackClick = { navController.popBackStack() },
                onSaveAddressClick = { navController.popBackStack() }
            )
        }
        composable(route = Screens.PropertyType.route) {
            PropertyTypeScreen(
                onBackClick = { navController.popBackStack() },
                onSaveClick = { navController.popBackStack() }
            )
        }
        composable(route = Screens.MaintenancePlans.route) {
            MaintenancePlansScreen(
                onBackClick = { navController.popBackStack() },
                onSelectPlan = { navController.popBackStack() }
            )
        }

        // Booking Screens
        composable(route = Screens.ScheduleSiteVisit.route) {
            ScheduleSiteVisitScreen(
                onBackClick = { navController.popBackStack() },
                onConfirmClick = {
                    navController.navigate(Screens.BookingSuccess.route) {
                        popUpTo(Screens.ScheduleSiteVisit.route) { inclusive = true }
                    }
                }
            )
        }
        composable(route = Screens.BookingSuccess.route) {
            BookingSuccessScreen(
                onGoToDashboard = {
                    navController.navigate(Screens.Home.route) {
                        popUpTo(Screens.Home.route) { inclusive = true }
                    }
                },
                onClose = {
                    navController.navigate(Screens.Home.route) {
                        popUpTo(Screens.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screens.QuotationReview.route) {
            QuotationReviewScreen(
                onBackClick = { navController.popBackStack() },
                onApproveClick = { /* Handle Approval */ },
                onRequestChangesClick = { /* Handle Changes */ }
            )
        }

        // Generic Service Detail
        composable(
            route = Screens.ServiceDetail.route,
            arguments = listOf(navArgument("serviceId") { type = NavType.StringType })
        ) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId") ?: ""
            ServiceDetailScreen(
                serviceId = serviceId,
                onBackClick = { navController.popBackStack() },
                onScheduleVisitClick = { navController.navigate(Screens.ScheduleSiteVisit.route) }
            )
        }
    }
}