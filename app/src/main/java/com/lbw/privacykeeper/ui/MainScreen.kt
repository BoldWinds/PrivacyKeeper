package com.lbw.privacykeeper.ui

import android.content.res.Configuration
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.lbw.privacykeeper.ui.nav.BottomNavBar
import com.lbw.privacykeeper.ui.nav.BottomNavGraph
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController)}
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainScreen() {
    PrivacyKeeperTheme {
        MainScreen()
    }
}