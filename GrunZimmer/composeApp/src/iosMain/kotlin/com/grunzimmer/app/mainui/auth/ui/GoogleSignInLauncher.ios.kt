package com.grunzimmer.app.mainui.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.interop.LocalUIViewController
import cocoapods.GoogleSignIn.GIDSignIn
import platform.UIKit.UIApplication

@Composable
actual fun rememberGoogleSignInLauncher(
    onResult: (String?) -> Unit
): () -> Unit {
    // Get the current View Controller to present the Google Sign-In sheet
    val viewController = LocalUIViewController.current

    return remember {
        {
            // Note: Ensure your Info.plist has the REVERSED_CLIENT_ID configured
            GIDSignIn.sharedInstance.signInWithPresentingViewController(viewController) { result, error ->
                if (error != null) {
                    println("Google Sign-In Error: ${error.localizedDescription}")
                    onResult(null)
                } else {
                    // Extract the ID Token from the result (GIDSignIn v7+)
                    val idToken = result?.user?.idToken?.tokenString
                    onResult(idToken)
                }
            }
        }
    }
}