package com.lbw.privacykeeper.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.lbw.privacykeeper.ui.utils.CustomDialog
import com.lbw.privacykeeper.utils.Utils.Companion.showToast
import privacykeeperv1.R

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
                showToast(
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
            TextField(
                value = password,
                onValueChange = { password=it},
                label = { Text(stringResource(id = R.string.password))},
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
    }

}
