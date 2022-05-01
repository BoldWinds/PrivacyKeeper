package com.lbw.privacykeeper.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun PrivacyKeeperApp(
    appContainer: AppContainer
) {
    //读取preference以确定是否打开guide
    val mainViewModel:MainViewModel = viewModel(
        factory = MainViewModel.provideFactory(appContainer.preferenceRepository)
    )

    mainViewModel.setShowGuidance()

    PrivacyKeeperTheme(mainViewModel.themeMode){

        GuideScreen(
            mainViewModel.showGuidance,
        ) {mainViewModel.openRegisterScreen()}

        RegisterScreen(
            mainViewModel.showRegister,
            saveUser = mainViewModel::saveUser,
            showMainScreen = mainViewModel::openMain,
            hasRegistered = mainViewModel::hasRegistered
        )

        if(mainViewModel.showMain){
            MainScreen(mainViewModel)
        }
    }
}