package com.lbw.privacykeeper.ui.init

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.ui.utils.CommonTextField
import com.lbw.privacykeeper.ui.utils.PasswordTextField
import com.lbw.privacykeeper.utils.Utils.Companion.showToast
import privacykeeperv1.R

@Composable
fun RegisterScreen(
    showRegisterScreen: Boolean,
    saveUser : (String, String, Context)->Unit,
    showMainScreen : ()->Unit,
    hasRegistered : (Context)->Unit,
) {
    val context = LocalContext.current

    var username by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }
    var passwordVisibility by remember{
        mutableStateOf(false)
    }

    var confirmPassword by remember{
        mutableStateOf("")
    }
    var confirmPasswordVisibility by remember{
        mutableStateOf(false)
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

            CommonTextField(
                value = username,
                onValueChange = {value:String->username=value},
                labelText = stringResource(id = R.string.username)
            )

            Spacer(modifier = Modifier.weight(0.05f))

            PasswordTextField(
                value = password,
                onValueChange = {value:String->password=value },
                visibility = passwordVisibility,
                changeVisible = {passwordVisibility=!passwordVisibility}
            )

            Spacer(modifier = Modifier.weight(0.05f))
            if(!password.equals(confirmPassword))
                Text(
                    text = stringResource(id = R.string.not_match),
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp
                )

            PasswordTextField(
                value = confirmPassword,
                onValueChange = {value:String->confirmPassword=value },
                visibility = confirmPasswordVisibility,
                changeVisible = {confirmPasswordVisibility=!confirmPasswordVisibility}
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Button(
                onClick = {
                    if (password!=confirmPassword) {
                        showToast(
                            context = context,
                            text = context.getString(R.string.register_failed)
                        )
                    }else{
                        hasRegistered(context)
                        saveUser(username,password,context)
                        showToast(
                            context,
                            context.getString(R.string.registered_successfully)
                        )
                        showMainScreen()
                    }
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
            saveUser = {_,_,_->},
            showMainScreen = {  },
            hasRegistered = {},
        )
    }
}