package com.lbw.privacykeeper.ui.user

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbw.privacykeeper.ui.password.PasswordText
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.ui.utils.PasswordTextField


@Composable
fun UserScreen(userViewModel: UserViewModel) {

    UserScreen(
        username = userViewModel.mutableUser.username,
        showConfirmDialog = userViewModel.showConfirmDialog,
        openConfirmDialog = userViewModel::openConfirmDialog,
        closeConfirmDialog = userViewModel::closeConfirmDialog,
        checkBiometric = userViewModel::openBiometricCheck,
        showUpdateDialog = userViewModel.permission,
        closeUpdateDialog = userViewModel::closeUpdateDialog,
        savePassword = userViewModel::updateUser
    )
}

@Composable
fun UserScreen(
    username : String,
    showConfirmDialog : Boolean,
    openConfirmDialog : ()->Unit,
    closeConfirmDialog : ()->Unit,
    checkBiometric : ()->Unit,
    showUpdateDialog : Boolean,
    closeUpdateDialog:()->Unit,
    savePassword : (String)->Unit
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
            openConfirmDialog = openConfirmDialog,
        )

    }

    ConfirmUpdateDialog(
        showDialog = showConfirmDialog,
        closeDialog = closeConfirmDialog,
        biometricCheck = checkBiometric
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
        UserScreen(
            username = "lbw",
            showConfirmDialog = true,
            openConfirmDialog = {},
            closeConfirmDialog = {},
            checkBiometric = {},
            showUpdateDialog = true,
            closeUpdateDialog = {},
            savePassword = {}
        )
    }
}