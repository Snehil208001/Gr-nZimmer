package com.grunzimmer.app.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ServiceDetails(
    val id: String,
    val title: String,
    val description: String,
    val icon: ImageVector
)