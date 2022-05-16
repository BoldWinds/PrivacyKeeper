package com.lbw.privacykeeper.ui.password

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbw.privacykeeper.model.Password
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.ui.utils.PermissionDialog
import privacykeeperv1.R


@Composable
fun PasswordScreen(
    openBiometricCheck: ()->Unit,
    showDialog : Boolean,
    openDialog : ()->Unit,
    closeDialog : ()->Unit,
    savePassword : (Password)->Unit,
    showPermissionDialog : Boolean,
    closePermissionDialog : ()->Unit,
    checkPermission : (String)->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.2f))

        Text(
            text = stringResource(id = R.string.secure_privacy),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight(2),
        )

        Spacer(modifier = Modifier.weight(0.15f))

        Button(onClick = { openDialog() }) {
            Text(text = stringResource(id = R.string.save_password))
        }

        Spacer(modifier = Modifier.weight(0.1f))

        Button(onClick = {openBiometricCheck()}) {
            Text(text = stringResource(id = R.string.show_password))
        }

        Spacer(modifier = Modifier.weight(0.4f))
    }

    PermissionDialog(
        showPermissionDialog = showPermissionDialog,
        closePermissionDialog = closePermissionDialog,
        checkPermission = checkPermission
    )

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
    PrivacyKeeperTheme {
        PasswordScreen(
            openBiometricCheck={},
            showDialog = false,
            openDialog = {},
            closeDialog = {},
            savePassword = {},
            showPermissionDialog = false,
            closePermissionDialog = {},
            checkPermission = {}
        )
    }
}



//显示密码UI
@Composable
fun PasswordScreen(
    passwordList : List<Password>,
    delete : (String)->Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
       itemsIndexed(passwordList){ _, item->

           PasswordCard(
               password = item,
               delete = delete
           )

           Spacer(modifier = Modifier.size(10.dp))
       }
    }
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
            delete = {}
        )
    }
}