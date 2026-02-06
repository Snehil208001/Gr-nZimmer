package com.grunzimmer.app.mainui.services.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Deck
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Window
import androidx.lifecycle.ViewModel
import com.grunzimmer.app.domain.model.ServiceDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ServiceListingViewModel : ViewModel() {

    private val _services = MutableStateFlow<List<ServiceDetails>>(emptyList())
    val services: StateFlow<List<ServiceDetails>> = _services.asStateFlow()

    init {
        loadServices()
    }

    private fun loadServices() {
        // Mock data matching the HTML content
        _services.value = listOf(
            ServiceDetails(
                id = "1",
                title = "Terrace Garden Setup",
                description = "Transform your roof into a green sanctuary",
                icon = Icons.Default.Deck
            ),
            ServiceDetails(
                id = "2",
                title = "Balcony Garden Setup",
                description = "Cozy green corners for compact spaces",
                icon = Icons.Default.Window // Using Window as proxy for Balcony
            ),
            ServiceDetails(
                id = "3",
                title = "Vertical Garden",
                description = "Lush green walls for modern aesthetics",
                icon = Icons.Default.Grass
            ),
            ServiceDetails(
                id = "4",
                title = "Kitchen / Vegetable Garden",
                description = "Grow your own organic produce",
                icon = Icons.Default.Restaurant // Using Restaurant/Nutrition proxy
            ),
            ServiceDetails(
                id = "5",
                title = "Garden Maintenance",
                description = "Expert care to keep your plants thriving",
                icon = Icons.Default.ContentCut
            )
        )
    }
}