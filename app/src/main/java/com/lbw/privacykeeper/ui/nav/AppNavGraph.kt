package com.lbw.privacykeeper.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                factory = PasswordViewModel.provideFactory(appContainer.passwordRepository, biometricCheckParameters)
            )
            PasswordScreen(
                openBiometricCheck = passwordViewModel::openBiometricCheck,
                showDialog = passwordViewModel.showSavePasswordDialog,
                openDialog = passwordViewModel::openDialog,
                closeDialog = passwordViewModel::closeDialog,
                savePassword = passwordViewModel::savePassword,
                navController = navController
            )
        }

        composable(route = AppScreen.Image.route){
            val imageViewModel : ImageViewModel = viewModel(
                factory = ImageViewModel.provideFactory(appContainer.imageRepository, biometricCheckParameters)
            )
            ImageScreen(
                setUri = imageViewModel::setNewUri,
                saveImage = imageViewModel::saveImage,
                openBiometricCheck = imageViewModel::openBiometricCheck,
                navController = navController
            )
        }

        composable(route = AppScreen.Video.route){
            val videoViewModel : VideoViewModel = viewModel(
                factory = VideoViewModel.provideFactory(appContainer.videoRepository,biometricCheckParameters)
            )
            VideoScreen(
                setUri=videoViewModel::setNewUri,
                saveVideo= videoViewModel::saveVideo,
                openBiometricCheck = videoViewModel::openBiometricCheck,
                navController = navController
            )
        }

        composable(route = AppSecondaryScreen.Password.route){
            val passwordViewModel : PasswordViewModel = viewModel(
                factory = PasswordViewModel.provideFactory(appContainer.passwordRepository, biometricCheckParameters)
            )
            passwordViewModel.readAllPassword()
            PasswordScreen(passwordList = passwordViewModel.passwordList)
        }

        composable(route = AppSecondaryScreen.Image.route){
            val imageViewModel : ImageViewModel = viewModel(
                factory = ImageViewModel.provideFactory(appContainer.imageRepository, biometricCheckParameters)
            )
            imageViewModel.getFilenames()
            ImageScreen(
                filenames = imageViewModel.filenames,
                showDialog = imageViewModel.showDialog,
                navController = navController,
                setOldFilename = imageViewModel::setOldFilename,
                openDialog = imageViewModel::openDialog,
                rename = imageViewModel::rename,
                closeDialog = imageViewModel::closeDialog
            )
        }

        composable(route = AppSecondaryScreen.Video.route){
            val videoViewModel : VideoViewModel = viewModel(
                factory = VideoViewModel.provideFactory(appContainer.videoRepository,biometricCheckParameters)
            )
            videoViewModel.getFilenames()
            VideoScreen(
                filenames = videoViewModel.filenames,
                showDialog = videoViewModel.showDialog,
                navController = navController,
                setOldFilename = videoViewModel::setOldFilename,
                openDialog = videoViewModel::openDialog,
                rename = videoViewModel::rename,
                closeDialog = videoViewModel::closeDialog
            )
        }

        composable(
            route = AppTertiaryScreen.Image.route + "/{path}",
            arguments = listOf(
                navArgument("path"){
                    type = NavType.StringType
                    nullable = false
                }
            )
        ){entry->
            Box(modifier = Modifier.wrapContentSize()){
                Text(text = entry.arguments?.getString("path")!!)
            }
        }

        composable(
            route = AppTertiaryScreen.Video.route + "/{path}",
            arguments = listOf(
                navArgument("path"){
                    type = NavType.StringType
                    nullable = false
                }
            )
        ){entry->
            Box(
                modifier = Modifier.wrapContentSize()
            ){
                Text(text = entry.arguments?.getString("path")!!)
            }
        }
    }
}