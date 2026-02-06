package com.grunzimmer.app.presentation.navigation

sealed class Screens(val route: String) {
    data object Splash : Screens("splash")
    data object Onboarding : Screens("onboarding")
    data object Login : Screens("login")
    data object OtpVerification : Screens("otp_verification") // Added this
    data object Home : Screens("home")
}