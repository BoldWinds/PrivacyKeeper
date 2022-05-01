package com.lbw.privacykeeper.ui

import android.util.Log
import androidx.compose.runtime.Composable
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import kotlin.math.log

@Composable
fun PrivacyKeeperApp(
    appContainer: AppContainer,
    mainViewModel : MainViewModel
) {
    Log.d("test","1")
    PrivacyKeeperTheme(mainViewModel.themeMode){
        Log.d("test","2")
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