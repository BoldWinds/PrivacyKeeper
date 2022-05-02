package com.lbw.privacykeeper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lbw.privacykeeper.PrivacyKeeperApplication


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as PrivacyKeeperApplication).container
        setContent {
            PrivacyKeeperApp(appContainer = appContainer)
        }
    }

}


