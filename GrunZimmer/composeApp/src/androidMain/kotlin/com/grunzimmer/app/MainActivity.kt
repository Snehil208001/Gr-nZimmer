package com.grunzimmer.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Register Activity for Phone Auth
        ActivityProvider.setActivity(this)

        setContent {
            App()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Clean up to prevent leaks
        if (ActivityProvider.getActivity() == this) {
            ActivityProvider.setActivity(this) // keeping it simple or set null
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}