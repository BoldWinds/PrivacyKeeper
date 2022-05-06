package com.lbw.privacykeeper.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
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

    if (showDialog){
        AlertDialog(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            onDismissRequest = {
                closeDialog()
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_info_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            },
            title = {
                Text(text = stringResource(id = R.string.update_password))
            },
            text ={
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
            } ,
            confirmButton = {
                TextButton(
                    onClick = {
                        if(password!=""){
                            try {
                                savePassword(password)
                                closeDialog()
                                showToast(
                                    show = true,
                                    context = context,
                                    text = context.getString(R.string.save_succeed)
                                )
                            }catch (e : Exception){
                                showToast(
                                    show = true,
                                    context = context,
                                    text = context.getString(R.string.save_failed)
                                )
                            }
                        }
                        password = ""
                    }
                ) {
                    Text(text = stringResource(id = R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
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
