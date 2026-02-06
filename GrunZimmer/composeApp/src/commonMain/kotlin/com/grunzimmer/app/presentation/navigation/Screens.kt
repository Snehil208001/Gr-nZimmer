package com.grunzimmer.app.presentation.navigation

sealed class Screens(val route: String) {
    data object Splash : Screens("splash")
    data object Onboarding : Screens("onboarding")
    data object Login : Screens("login")
    data object OtpVerification : Screens("otp_verification")
    data object ProfileSetup : Screens("profile_setup")
    data object Home : Screens("home")

    // New Route for Service Details
    data object ServiceDetail : Screens("service_detail/{serviceId}") {
        fun createRoute(serviceId: String) = "service_detail/$serviceId"
    }
}