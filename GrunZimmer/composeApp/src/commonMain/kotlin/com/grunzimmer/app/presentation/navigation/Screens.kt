package com.grunzimmer.app.presentation.navigation

sealed class Screens(val route: String) {
    data object Splash : Screens("splash")
    data object Onboarding : Screens("onboarding")
    data object Login : Screens("login")
    data object OtpVerification : Screens("otp_verification")
    data object ProfileSetup : Screens("profile_setup")
    data object Home : Screens("home")

    // Service Routes
    data object TerraceGarden : Screens("terrace_garden_setup")
    data object BalconyGarden : Screens("balcony_garden_setup")
    data object VerticalGarden : Screens("vertical_garden_setup")
    data object KitchenGarden : Screens("kitchen_garden_setup")
    data object GardenMaintenance : Screens("garden_maintenance")

    // Profile Routes
    data object AddressDetails : Screens("address_details")
    data object PropertyType : Screens("property_type")
    data object MaintenancePlans : Screens("maintenance_plans")

    // Booking Routes
    data object ScheduleSiteVisit : Screens("schedule_site_visit")
    data object BookingSuccess : Screens("booking_success")
    data object QuotationReview : Screens("quotation_review") // <--- Added this

    data object ServiceDetail : Screens("service_detail/{serviceId}") {
        fun createRoute(serviceId: String) = "service_detail/$serviceId"
    }
}