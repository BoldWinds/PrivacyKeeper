package com.lbw.privacykeeper.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.lbw.privacykeeper.PrivacyKeeperApplication


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val appContainer = (application as PrivacyKeeperApplication).container
        val biometricCheckParameters = (application as PrivacyKeeperApplication).biometricCheckParameters

        setContent {
            PrivacyKeeperApp(appContainer = appContainer, biometricCheckParameters = biometricCheckParameters)
        }
    }


}
