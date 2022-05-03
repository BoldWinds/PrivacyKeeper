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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
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
    var confirmPassword by remember{
        mutableStateOf("")
    }

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
                        savePassword(password)
                        closeDialog()
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
