package com.lbw.privacykeeper.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme


@Composable
fun PrivacyKeeperApp(
    appContainer: AppContainer
) {
    //读取preference以确定是否打开guide
    val mainViewModel:MainViewModel = viewModel(
        factory = MainViewModel.provideFactory(appContainer.preferenceRepository)
    )

    PrivacyKeeperTheme(mainViewModel.themeMode){

        GuideScreen(
            mainViewModel.showGuidance,
        ) {mainViewModel.openRegisterScreen()}

        RegisterScreen(
            mainViewModel.showRegister,
            saveUser = mainViewModel::saveUser,
            showMainScreen = mainViewModel::openMain
        )

        if(mainViewModel.showMain){
            MainScreen(mainViewModel)
        }
    }
}