package com.lbw.privacykeeper.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.lbw.privacykeeper.BottomNavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {}
    ) {
        BottomNavGraph(navController = navController)
    }
}