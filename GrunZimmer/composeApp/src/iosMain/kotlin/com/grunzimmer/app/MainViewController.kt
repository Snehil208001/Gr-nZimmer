package com.grunzimmer.app

import androidx.compose.ui.window.ComposeUIViewController
import com.grunzimmer.app.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}