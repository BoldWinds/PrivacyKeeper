package com.lbw.privacykeeper.ui.utils

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.utils.Utils
import privacykeeperv1.R


//给Dialog提升代码复用性
@Composable
fun CustomDialog(
    showDialog : Boolean = false,
    title : String = "Dialog",
    closeDialog: ()->Unit = {},
    onConfirm : ()->Unit = {},
    onDismiss : ()->Unit = {},
    content : @Composable ()->Unit = {}
) {

    if (showDialog){
        AlertDialog(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            onDismissRequest = {
                closeDialog()
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_warning_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            },
            title = {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            text = {
                content()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm()
                        closeDialog()
                    }
                ) {
                    Text(text = stringResource(id = R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                        closeDialog()
                    }
                ) {
                    Text(text = stringResource(id = R.string.dismiss))
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 10.dp
        )
    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCustomDialog() {
    PrivacyKeeperTheme {
        Box(
          modifier = Modifier.fillMaxSize()
        ){
            CustomDialog(showDialog = true)
        }
    }
}


@Composable
fun PermissionDialog(
    showPermissionDialog : Boolean,
    closePermissionDialog : ()->Unit,
    checkPermission : (String)->Unit
) {
    var password by remember {
        mutableStateOf("")
    }

    CustomDialog(
        showDialog = showPermissionDialog,
        title = stringResource(id = R.string.check_permission),
        closeDialog = closePermissionDialog,
        onConfirm = { checkPermission(password) },
        onDismiss = { password="" }
    ){
        PasswordTextField(
            value = password,
            onValueChange = {password = it}
        )
    }
}


@Composable
fun RenameDialog(
    showDialog: Boolean,
    closeDialog: () -> Unit,
    rename : (String)->Unit
) {
    var newName by remember {
        mutableStateOf("")
    }

    CustomDialog(
        showDialog = showDialog,
        title = stringResource(id = R.string.rename),
        closeDialog = closeDialog,
        onConfirm = {
            rename(newName)
            newName = ""
        },
        onDismiss = {
            newName = ""
        }
    ) {
        CommonTextField(
            value = newName,
            onValueChange = {newName = it},
            labelText = stringResource(id = R.string.rename)
        )
    }
}


@Composable
fun UpdateDialog(
    showDialog : Boolean,
    closeDialog : ()->Unit,
    savePassword : (String)->Unit
){
    var password by remember{
        mutableStateOf("")
    }

    val context = LocalContext.current

    CustomDialog(
        showDialog = showDialog,
        title = stringResource(id = R.string.update_password),
        closeDialog = { closeDialog() },
        onConfirm = {
            if (password != "") {
                savePassword(password)
                closeDialog()
                Utils.showToast(
                    context = context,
                    text = context.getString(R.string.save_succeed)
                )
            }
        },
        onDismiss = { password = "" }
    ){
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            PasswordTextField(
                value = password,
                onValueChange = { password=it},
            )
        }
    }

}