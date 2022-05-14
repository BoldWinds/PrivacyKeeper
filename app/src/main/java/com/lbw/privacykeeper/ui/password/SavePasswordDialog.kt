package com.lbw.privacykeeper.ui.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lbw.privacykeeper.model.Password
import com.lbw.privacykeeper.ui.utils.CommonTextField
import com.lbw.privacykeeper.ui.utils.PasswordTextField
import com.lbw.privacykeeper.utils.Utils.Companion.showToast
import privacykeeperv1.R

@Composable
fun SavePasswordDialog(
    showDialog : Boolean,
    closeDialog : ()->Unit,
    savePassword : (Password)->Unit
) {
    val context = LocalContext.current

    var website by remember{
        mutableStateOf("")
    }

    var username by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    var visibility by remember {
        mutableStateOf(false)
    }

    if(showDialog){
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
                Text(text = stringResource(id = R.string.warning))
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "请输入您要保存的密码："
                    )

                    CommonTextField(
                        value = website,
                        onValueChange = {value:String->website = value},
                        labelText = stringResource(id = R.string.website)
                    )

                    CommonTextField(value = username,
                        onValueChange = {value:String->username = value},
                        labelText = stringResource(id = R.string.username)
                    )

                    PasswordTextField(
                        value = password,
                        onValueChange = {value:String->password=value},
                        visibility = visibility,
                        changeVisible = {visibility = !visibility}
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if(website!=""&&username!=""&&password!=""){
                            closeDialog()
                                savePassword(Password(website,username, password))
                                showToast(
                                    context = context,
                                    text = context.getString(R.string.save_succeed)
                                )
                        }else{
                            showToast(
                                context = context,
                                text = context.getString(R.string.input_error)
                            )
                        }
                        website = ""
                        username = ""
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