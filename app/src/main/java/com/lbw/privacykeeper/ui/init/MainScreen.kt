package com.lbw.privacykeeper.ui.init

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.ui.nav.BottomNavBar
import com.lbw.privacykeeper.ui.nav.AppNavGraph
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import privacykeeperv1.R

@Composable
fun MainScreen(
    appContainer: AppContainer,
    biometricCheckParameters: BiometricCheckParameters,
    mainViewModel: MainViewModel,
) {
    val navController = rememberNavController()
    mainViewModel.readUser()

    Scaffold(
        topBar = {
            TopBar(
                onBack = {
                    navController.popBackStack()
                },
                content = {
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
                            contentDescription = "Theme Mode",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            )
        },
        content = {
            AppNavGraph(
                navController = navController,
                appContainer = appContainer,
                biometricCheckParameters = biometricCheckParameters,
                user = mainViewModel.user
            )
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    )
}
