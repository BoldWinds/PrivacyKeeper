package com.lbw.privacykeeper.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lbw.privacykeeper.model.User
import com.lbw.privacykeeper.ui.nav.BottomBarScreen
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import privacykeeperv1.R

@Composable
fun RegisterScreen(
    showRegisterScreen: Boolean,
    saveUser : (String,String)->Unit,
    showMainScreen : ()->Unit,
) {
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
                    saveUser(username,password)
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
        /*val mainViewModel : MainViewModel = viewModel()
        RegisterScreen(
            true,
        //    saveUser = {mainViewModel.saveUser()},
            showMainScreen = { mainViewModel.showMain }
        )*/
    }
}