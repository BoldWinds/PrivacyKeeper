package com.lbw.privacykeeper.ui.utils

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import privacykeeperv1.R

@Composable
fun TopBar() {
    SmallTopAppBar(
        title = {
            Text(text = "PrivacyKeeper")
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Localized description"
                )
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_lock_fill0_wght400_grad0_opsz48),
                    contentDescription = "lock"
                )
            }
        }

    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTopAppBar() {
    TopBar()
}