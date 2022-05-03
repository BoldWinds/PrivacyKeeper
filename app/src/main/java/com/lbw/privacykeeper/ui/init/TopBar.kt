package com.lbw.privacykeeper.ui.init

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import privacykeeperv1.R

@Composable
fun TopBar(content : @Composable ()->Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "PrivacyKeeper")
        },
        actions = {
            content()
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
    )

}


@Preview
@Composable
fun PreviewTopAppBar() {
    TopBar {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_lightmode_foreground),
                contentDescription = "Theme Mode"
            )
        }
    }
}