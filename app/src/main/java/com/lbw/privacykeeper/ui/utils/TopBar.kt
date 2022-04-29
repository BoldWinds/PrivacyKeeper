package com.lbw.privacykeeper.ui.utils

import android.content.res.Configuration
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lbw.privacykeeper.ui.MainViewModel
import com.lbw.privacykeeper.ui.ThemeMode
import privacykeeperv1.R

@Composable
fun TopBar(content : @Composable ()->Unit) {
    SmallTopAppBar(
        title = {
            Text(text = "PrivacyKeeper")
        },
        actions = {
            content()
            IconButton(onClick = { /*Do sth*/ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_lock_fill0_wght400_grad0_opsz48),
                    contentDescription = "lock"
                )
            }
        },

    )

}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTopAppBar() {
    val mainViewModel : MainViewModel = viewModel()

    TopBar {
        IconButton(onClick = {}) {
            Icon(
                painter = if (mainViewModel.themeMode == ThemeMode.DarkMode)
                            painterResource(id = R.drawable.ic_darkmode_foreground)
                          else painterResource(id = R.drawable.ic_lightmode_foreground),
                contentDescription = "Theme Mode"
            )
        }
    }
}