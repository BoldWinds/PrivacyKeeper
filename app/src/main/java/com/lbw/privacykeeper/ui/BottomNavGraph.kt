package com.lbw.privacykeeper

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lbw.privacykeeper.ui.BottomBarScreen
import com.lbw.privacykeeper.ui.image.ImageScreen
import com.lbw.privacykeeper.ui.password.PasswordScreen
import com.lbw.privacykeeper.ui.user.UserScreen
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