package com.lbw.privacykeeper.ui.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.ui.image.ImageScreen
import com.lbw.privacykeeper.ui.image.ImageViewModel
import com.lbw.privacykeeper.ui.password.PasswordScreen
import com.lbw.privacykeeper.ui.password.PasswordViewModel
import com.lbw.privacykeeper.ui.user.UserScreen
import com.lbw.privacykeeper.ui.user.UserViewModel
import com.lbw.privacykeeper.ui.video.VideoScreen
import com.lbw.privacykeeper.ui.video.VideoViewModel
import com.lbw.privacykeeper.utils.BiometricCheckParameters

@Composable
fun AppNavGraph(
    appContainer: AppContainer,
    navController: NavHostController,
    biometricCheckParameters: BiometricCheckParameters
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.User.route
    ){
        composable(route = AppScreen.User.route){
            val userViewModel : UserViewModel = viewModel(
                factory = UserViewModel.provideFactory(appContainer.preferenceRepository, biometricCheckParameters)
            )
            userViewModel.getThisUser()
            userViewModel.setUser()
            UserScreen(userViewModel)
        }

        composable(route = AppScreen.Password.route){
            val passwordViewModel : PasswordViewModel = viewModel(
                factory = PasswordViewModel.provideFactory(appContainer.passwordRepository, biometricCheckParameters,navController)
            )
            PasswordScreen(passwordViewModel)
        }

        composable(route = AppScreen.Image.route){
            val imageViewModel : ImageViewModel = viewModel(
                factory = ImageViewModel.provideFactory(appContainer.imageRepository, biometricCheckParameters)
            )
            ImageScreen(imageViewModel)
        }

        composable(route = AppScreen.Video.route){
            val videoViewModel : VideoViewModel = viewModel(
                factory = VideoViewModel.provideFactory(appContainer.videoRepository,biometricCheckParameters)
            )
            VideoScreen(videoViewModel)
        }

        composable(route = AppSecondaryScreen.Password.route){
            val passwordViewModel : PasswordViewModel = viewModel(
                factory = PasswordViewModel.provideFactory(appContainer.passwordRepository, biometricCheckParameters,navController)
            )
            passwordViewModel.readAllPassword()
            PasswordScreen(passwordList = passwordViewModel.passwordList)
        }

        composable(route = AppSecondaryScreen.Image.route){

        }

        composable(route = AppSecondaryScreen.Video.route){

        }

    }
}