package com.lbw.privacykeeper.ui

import androidx.compose.runtime.Composable
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme

@Composable
fun PrivacyKeeperApp(
    appContainer: AppContainer,
    mainViewModel : MainViewModel
) {
    PrivacyKeeperTheme(mainViewModel.themeMode){
        //检查配置文件，进行设置
    //    val boot : Boolean? = appContainer.preferenceRepository.read()

        GuideScreen(
            mainViewModel.showGuidance,
        ) {mainViewModel.openRegisterScreen()}

        RegisterScreen(
            mainViewModel.showRegister,
            saveUser = mainViewModel::saveUser,
            showMainScreen = {mainViewModel.openMain()}
        )

        if(mainViewModel.showMain){
            MainScreen(mainViewModel)
        }
    }
}