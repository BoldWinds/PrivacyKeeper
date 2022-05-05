package com.lbw.privacykeeper.utils

import android.content.res.Configuration
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import privacykeeperv1.R

@Composable
fun CopyPasswordButton(
    password : String
) {
    val clipboardManager = LocalClipboardManager.current

    val context = LocalContext.current

    OutlinedButton(
        onClick = {
            Utils.clipString(
                clipboardManager = clipboardManager,
                data = password,
            )

            Utils.showToast(
                show = true,
                context = context,
                text = context.getString(R.string.has_copied)
            )
        },
        elevation = ButtonDefaults.buttonElevation(2.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)

    ) {
        Text(
            text = stringResource(id = R.string.copy),
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCopyButton(){
    PrivacyKeeperTheme {
        CopyPasswordButton(password = "123")
    }
}