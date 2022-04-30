package com.lbw.privacykeeper.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme

@Composable
fun RegisterScreen() {
    Row() {
        
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewRegisterScreen() {
    PrivacyKeeperTheme{
        RegisterScreen()
    }
}