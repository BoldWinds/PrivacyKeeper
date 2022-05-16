package com.lbw.privacykeeper.ui.user

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.ui.utils.PermissionDialog
import com.lbw.privacykeeper.ui.utils.UpdateDialog


@Composable
fun UserScreen(
    username : String = "",
    onBiometricCheck: ()->Unit={},
    showUpdateDialog : Boolean = false,
    closeUpdateDialog:()->Unit = {},
    savePassword : (String)->Unit = {},
    showPermissionDialog : Boolean = false,
    closePermissionDialog : ()->Unit = {},
    checkPermission : (String)->Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),

        ) {

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .size(15.dp))

        UserBar(
            username = username,
            openConfirmDialog = onBiometricCheck,
        )

    }

    PermissionDialog(
        showPermissionDialog = showPermissionDialog,
        closePermissionDialog = closePermissionDialog,
        checkPermission = checkPermission
    )

    UpdateDialog(
        showDialog = showUpdateDialog,
        closeDialog = closeUpdateDialog,
        savePassword = savePassword
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewUserScreen() {
    PrivacyKeeperTheme {
        UserScreen()
    }
}
