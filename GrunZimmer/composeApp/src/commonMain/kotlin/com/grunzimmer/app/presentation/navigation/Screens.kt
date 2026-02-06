// composeApp/src/commonMain/kotlin/com/grunzimmer/app/presentation/navigation/Screens.kt
package com.grunzimmer.app.presentation.navigation

sealed class Screens(val route: String) {
    data object Splash : Screens("splash")
    data object Onboarding : Screens("onboarding")
    data object Login : Screens("login")
    data object OtpVerification : Screens("otp_verification")
    data object ProfileSetup : Screens("profile_setup") // Added New Route
    data object Home : Screens("home")
    data object ServiceListing : Screens("service_listing")
}