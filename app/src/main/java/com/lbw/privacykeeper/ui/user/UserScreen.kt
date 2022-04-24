package com.lbw.privacykeeper.ui.user

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lbw.privacykeeper.ui.image.ImageScreen
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme


@Composable
fun UserScreen() {
    Box(
        modifier = Modifier.background(MaterialTheme.colors.onPrimary)
    ){
        Text(
            text = "User",
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewUserScreen() {
    PrivacyKeeperTheme {
        UserScreen()
    }
}