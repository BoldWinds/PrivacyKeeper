package com.lbw.privacykeeper.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.lbw.privacykeeper.BottomNaviGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {}
    ) {
        BottomNaviGraph(navController = navController)
    }
}