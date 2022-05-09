package com.lbw.privacykeeper.ui.init

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import privacykeeperv1.R

@Composable
fun TopBar(
    content : @Composable ()->Unit,
    onBack : ()->Unit
) {
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
        navigationIcon = {
            IconButton(
                onClick = {
                    onBack()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_foreground),
                    contentDescription = "back",
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    )

}


@Preview
@Composable
fun PreviewTopAppBar() {
    TopBar(
        onBack = {},
        content = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lightmode_foreground),
                    contentDescription = "Theme Mode"
                )
            }
        }
    )
}