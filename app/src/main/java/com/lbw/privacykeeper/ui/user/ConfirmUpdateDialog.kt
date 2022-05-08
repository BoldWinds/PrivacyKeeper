package com.lbw.privacykeeper.ui.user

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lbw.privacykeeper.ui.utils.CustomDialog
import privacykeeperv1.R


@Composable
fun ConfirmUpdateDialog(
    showDialog : Boolean,
    closeDialog: ()->Unit,
    biometricCheck: ()->Unit
) {
    CustomDialog(
        showDialog = showDialog,
        title = stringResource(id = R.string.warning),
        closeDialog = { closeDialog() },
        onConfirm = { biometricCheck() },
        onDismiss = { }
    ){
        Text(text = stringResource(id = R.string.confirm_dialog_text))
    }

}
