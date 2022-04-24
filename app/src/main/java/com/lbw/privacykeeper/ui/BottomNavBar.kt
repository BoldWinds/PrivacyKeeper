package com.lbw.privacykeeper.ui


import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController : NavHostController) {
    val screens = listOf(
        BottomBarScreen.Password,
        BottomBarScreen.Image,
        BottomBarScreen.Video,
        BottomBarScreen.User
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination


    NavigationBar(

    ) {

    }
}



