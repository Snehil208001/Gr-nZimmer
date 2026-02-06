package com.grunzimmer.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.grunzimmer.app.presentation.navigation.AppNavigation

@Composable
fun App() {
    MaterialTheme {
        AppNavigation()
    }
}