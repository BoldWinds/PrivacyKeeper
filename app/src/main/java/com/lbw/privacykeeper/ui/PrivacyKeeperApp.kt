package com.lbw.privacykeeper.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.ui.init.GuideScreen
import com.lbw.privacykeeper.ui.init.MainScreen
import com.lbw.privacykeeper.ui.init.MainViewModel
import com.lbw.privacykeeper.ui.init.RegisterScreen
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.utils.BiometricCheckParameters


@Composable
fun PrivacyKeeperApp(
    appContainer: AppContainer,
    biometricCheckParameters: BiometricCheckParameters
) {
    //读取preference以确定是否打开guide
    val mainViewModel: MainViewModel = viewModel(
        factory = MainViewModel.provideFactory(
            preferenceRepository = appContainer.preferenceRepository
        )
    )

    mainViewModel.setShowGuidance()

    PrivacyKeeperTheme(mainViewModel.themeMode){

        GuideScreen(
            mainViewModel.showGuidance,
        ) {mainViewModel.openRegisterScreen()}

        RegisterScreen(
            showRegisterScreen = mainViewModel.showRegister,
            saveUser = mainViewModel::saveUser,
            showMainScreen = mainViewModel::openMain,
            hasRegistered = mainViewModel::hasRegistered,
        )

        if(mainViewModel.showMain){

            MainScreen(
                mainViewModel = mainViewModel,
                appContainer = appContainer,
                biometricCheckParameters = biometricCheckParameters
            )
        }
    }
}