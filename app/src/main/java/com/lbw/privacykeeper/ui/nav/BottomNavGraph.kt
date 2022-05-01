package com.lbw.privacykeeper.ui.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lbw.privacykeeper.ui.image.ImageScreen
import com.lbw.privacykeeper.ui.password.PasswordScreen
import com.lbw.privacykeeper.ui.user.UserScreen
import com.lbw.privacykeeper.ui.user.UserViewModel
import com.lbw.privacykeeper.ui.video.VideoScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.User.route
    ){
        composable(route = BottomBarScreen.User.route){
            UserScreen()
        }

        composable(route = BottomBarScreen.Password.route){
            PasswordScreen()
        }

        composable(route = BottomBarScreen.Image.route){
            ImageScreen()
        }

        composable(route = BottomBarScreen.Video.route){
            VideoScreen()
        }

    }
}