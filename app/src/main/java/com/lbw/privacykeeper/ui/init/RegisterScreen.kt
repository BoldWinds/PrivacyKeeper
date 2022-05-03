package com.lbw.privacykeeper.ui.init

import android.content.res.Configuration
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.utils.Utils.Companion.showToast
import privacykeeperv1.R

@Composable
fun RegisterScreen(
    showRegisterScreen: Boolean,
    saveUser : (String,String)->Unit,
    showMainScreen : ()->Unit,
    hasRegistered : ()->Unit,
) {
    val context = LocalContext.current
    var username by remember{
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
    var confirmPassword by remember{
        mutableStateOf("")
    }

    if (showRegisterScreen){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.3f))

            Text(
                text = stringResource(id = R.string.start_text),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.weight(0.1f))

            TextField(
                value = username,
                onValueChange ={username = it},
                label = {Text(stringResource(id = R.string.username))}
            )

            Spacer(modifier = Modifier.weight(0.05f))

            TextField(
                value = password,
                onValueChange = { password=it},
                label = { Text(stringResource(id = R.string.password))},
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.weight(0.05f))
            if(!password.equals(confirmPassword))
                Text(
                    text = stringResource(id = R.string.not_match),
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp
                )

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword=it},
                label = { Text(stringResource(id = R.string.confirm_password))},
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Button(
                onClick = {
                    hasRegistered()
                    saveUser(username,password)
                    showToast(
                        true,
                        context,
                        context.getString(R.string.registered_successfully)
                    )
                    showMainScreen()
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .weight(0.1f)
                    .fillMaxWidth(0.5f)
            ) {
                Text(text = stringResource(id = R.string.register))
            }

            Spacer(modifier = Modifier.weight(0.35f))
        }

    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewRegisterScreen() {
    PrivacyKeeperTheme{
        RegisterScreen(
            true,
            saveUser = {a:String,b:String->},
            showMainScreen = {  },
            hasRegistered = {},
        )
    }
}