package com.lbw.privacykeeper.ui.init

import android.content.res.Configuration
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.ui.nav.BottomNavBar
import com.lbw.privacykeeper.ui.nav.AppNavGraph
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import privacykeeperv1.R

@Composable
fun MainScreen(
    appContainer: AppContainer,
    biometricCheckParameters: BiometricCheckParameters,
    mainViewModel: MainViewModel
) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopBar {
                IconButton(onClick = {
                    Log.d("click",mainViewModel.themeMode.toString())

                    if (mainViewModel.isLight())
                        mainViewModel.setDarkMode()
                    else
                        mainViewModel.setLightMode()

                }) {
                    Icon(
                        painter = if (mainViewModel.isLight())
                                      painterResource(id = R.drawable.ic_lightmode_foreground)
                                  else painterResource(id = R.drawable.ic_darkmode_foreground),
                        contentDescription = "Theme Mode"
                    )
                }
            }
        },

        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        AppNavGraph(
            navController = navController,
            appContainer = appContainer,
            biometricCheckParameters = biometricCheckParameters
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainScreen() {
    PrivacyKeeperTheme{
    //    MainScreen(mainViewModel = viewModel())
    }
}