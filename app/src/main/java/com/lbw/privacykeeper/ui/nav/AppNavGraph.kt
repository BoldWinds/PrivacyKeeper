package com.lbw.privacykeeper.ui.nav

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.ui.image.*
import com.lbw.privacykeeper.ui.password.PasswordScreen
import com.lbw.privacykeeper.ui.password.PasswordViewModel
import com.lbw.privacykeeper.ui.user.UserScreen
import com.lbw.privacykeeper.ui.user.UserViewModel
import com.lbw.privacykeeper.ui.video.*
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import java.io.File


@SuppressLint("CoroutineCreationDuringComposition")
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
            val imageViewModel : PrimaryImageViewModel = viewModel(
                factory = PrimaryImageViewModel.provideFactory(appContainer.imageRepository, biometricCheckParameters)
            )
            ImageScreen(
                setUri = imageViewModel::setNewUri,
                saveImage = imageViewModel::saveImage,
                openBiometricCheck = imageViewModel::openBiometricCheck,
                navController = navController
            )
        }

        composable(route = AppScreen.Video.route){
            val videoViewModel : PrimaryVideoViewModel = viewModel(
                factory = PrimaryVideoViewModel.provideFactory(appContainer.videoRepository,biometricCheckParameters)
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
            PasswordScreen(
                passwordList = passwordViewModel.passwordList,
                delete = passwordViewModel::delete
            )
        }

        composable(route = AppSecondaryScreen.Image.route){
            val imageViewModel : SecondaryImageViewModel = viewModel(
                factory = SecondaryImageViewModel.provideFactory(appContainer.imageRepository)
            )
            imageViewModel.getFilenames()
            ImageScreen(
                filenames = imageViewModel.filenames,
                showDialog = imageViewModel.showDialog,
                navController = navController,
                setOldFilename = imageViewModel::setOldFilename,
                openDialog = imageViewModel::openDialog,
                rename = imageViewModel::rename,
                closeDialog = imageViewModel::closeDialog,
                delete = imageViewModel::delete
            )
        }

        composable(route = AppSecondaryScreen.Video.route){
            val videoViewModel : SecondaryVideoViewModel = viewModel(
                factory = SecondaryVideoViewModel.provideFactory(appContainer.videoRepository)
            )
            videoViewModel.getFilenames()
            VideoScreen(
                filenames = videoViewModel.filenames,
                showDialog = videoViewModel.showDialog,
                navController = navController,
                setOldFilename = videoViewModel::setOldFilename,
                openDialog = videoViewModel::openDialog,
                rename = videoViewModel::rename,
                closeDialog = videoViewModel::closeDialog,
                delete = videoViewModel::delete
            )
        }

        composable(
            route = AppTertiaryScreen.Image.route + "/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    nullable = false
                }
            )
        ){entry->
            val imageViewModel : TertiaryImageViewModel = viewModel(
                factory = TertiaryImageViewModel.provideFactory(appContainer.imageRepository)
            )
            val filename : String = entry.arguments?.getString("name")!!
            Log.d("filename",filename)
            imageViewModel.setImageBitmap(filename)
            Image(
                bitmap = imageViewModel.getImageBitmap(),
                contentDescription = "Full Screen",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f),
            )
        }

        composable(
            route = AppTertiaryScreen.Video.route + "/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    nullable = false
                }
            )
        ){entry->
            val videoViewModel : TertiaryVideoViewModel= viewModel(
                factory = TertiaryVideoViewModel.provideFactory(appContainer.videoRepository)
            )
            videoViewModel.deleteDecrypted()
            val filename = entry.arguments?.getString("name")!!
            val context = LocalContext.current

            videoViewModel.setFilename(entry.arguments?.getString("name")!!)
            videoViewModel.readVideo()

            val uri = Uri.fromFile(File(File(File(context.filesDir,"videos"),"decrypted"),filename))

            DisplayVideo(
                showPlayer = videoViewModel.isJobFinished,
                uri = uri,
                openVideo = videoViewModel::openVideo
            )

        }
    }
}