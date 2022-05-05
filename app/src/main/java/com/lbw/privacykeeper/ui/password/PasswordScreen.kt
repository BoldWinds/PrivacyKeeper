package com.lbw.privacykeeper.ui.password

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lbw.privacykeeper.model.Password
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme

@Composable
fun PasswordScreen(passwordViewModel: PasswordViewModel) {
    if(!passwordViewModel.permission){
        PasswordScreen(
            openBiometricCheck = passwordViewModel::openBiometricCheck,
            showDialog = passwordViewModel.showSavePasswordDialog,
            openDialog = passwordViewModel::openDialog,
            closeDialog = passwordViewModel::closeDialog,
            savePassword = passwordViewModel::savePassword,
        )
    }else{
        passwordViewModel.readAllPassword()
        PasswordScreen(
            passwordList = passwordViewModel.passwordList
        )
    }

    /*var text by remember{
            mutableStateOf("")
        }
        var visible by remember{
            mutableStateOf(false)
        }
        PasswordTextField(
            value = text,
            onValueChange = {a : String->text = a},
            visible = visible,
            changeVisible = {visible = !visible}
        )*/

    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background),
        ){
        PasswordCard(password = Password("1","2","3"))
    }*/
}



@Composable
fun PasswordScreen(
    openBiometricCheck: ()->Unit,
    showDialog : Boolean,
    openDialog : ()->Unit,
    closeDialog : ()->Unit,
    savePassword : (Password)->Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.4f))

        Button(onClick = { openDialog() }) {
            Text(text = "save password")
        }

        Spacer(modifier = Modifier.weight(0.1f))

        Button(onClick = {openBiometricCheck()}) {
            Text(text = "show password")
        }

        Spacer(modifier = Modifier.weight(0.4f))
    }

    SavePasswordDialog(
        showDialog = showDialog,
        closeDialog = closeDialog,
        savePassword = savePassword,
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewPasswordScreen1() {

}



//显示密码UI
@Composable
fun PasswordScreen(
    passwordList : List<Password>
) {

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewPasswordScreen2() {
    PrivacyKeeperTheme {
        PasswordScreen(
            passwordList = mutableListOf<Password>(
                Password("JetBrains","lbw","lbwnb"),
                Password("Google","lbw","lbwnb"),
                Password("Apple","lbw","lbwnb"),
                Password("Microsoft","lbw","lbwnb"),
            ),

        )
    }
}