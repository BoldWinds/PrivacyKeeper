package com.lbw.privacykeeper.ui.utils

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import privacykeeperv1.R

@Composable
fun PasswordTextField(
    value : String,
    onValueChange : (String)->Unit,
    visible : Boolean,
    changeVisible : ()->Unit
) {
    TextField(
        value = value,
        onValueChange =  {onValueChange(it)} ,
        keyboardActions = KeyboardActions(),
        maxLines = 1,
        label = { Text(text = stringResource(id = R.string.password))},

    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewPasswordTextField(){
    PrivacyKeeperTheme {
        PasswordTextField(
            value = "password example",
            onValueChange = {},
            visible = false,
            changeVisible = {}
        )
    }
}

@Composable
fun CommonTextField(
    value : String,
    onValueChange : (String)->Unit,
    labelText : String,
) {

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCommonTextField(){
    PrivacyKeeperTheme {
        CommonTextField(
            value = "",
            onValueChange = {},
            labelText = "example"
        )
    }
}