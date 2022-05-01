package com.lbw.privacykeeper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.lbw.privacykeeper.PrivacyKeeperApplication


class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    val appContainer = (application as PrivacyKeeperApplication).container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrivacyKeeperApp(appContainer = appContainer, mainViewModel = mainViewModel)
        }
    }

}


