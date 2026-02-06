package com.grunzimmer.app.mainui.services.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grunzimmer.app.mainui.services.viewmodel.ServiceListingViewModel
import com.grunzimmer.app.presentation.components.ServiceCard
import com.grunzimmer.app.presentation.components.ServiceListingTopBar
import com.grunzimmer.app.presentation.theme.BackgroundLight
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ServiceListingScreen(
    onServiceClick: (String) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val viewModel = koinViewModel<ServiceListingViewModel>()
    val services by viewModel.services.collectAsState()

    // Internal Scaffold only for TopBar, NO BottomBar here
    Scaffold(
        topBar = {
            ServiceListingTopBar(
                onBackClick = onBackClick,
                onSearchClick = { /* Handle search */ }
            )
        },
        containerColor = BackgroundLight
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(services) { service ->
                    ServiceCard(
                        title = service.title,
                        description = service.description,
                        icon = service.icon,
                        onClick = { onServiceClick(service.id) }
                    )
                }
            }
        }
    }
}