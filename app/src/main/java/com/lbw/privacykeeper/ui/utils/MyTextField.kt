package com.lbw.privacykeeper.ui.utils

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import privacykeeperv1.R

//封装密码OutlinedTextField
@Composable
fun PasswordTextField(
    value : String,
    onValueChange : (String)->Unit,
    maxLines : Int = 1,
    singleLine : Boolean = true,

) {

    var visibility by remember{
        mutableStateOf(false)
    }


    OutlinedTextField(
        value = value,
        onValueChange =  {onValueChange(it)} ,
        maxLines = maxLines,
        label = {
            Text(
                text = stringResource(id = R.string.password)
            )
        },
        singleLine = singleLine,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions(),
        visualTransformation = if(!visibility) PasswordVisualTransformation()
                                else VisualTransformation.None,
        trailingIcon = {
            IconButton(
                onClick = {visibility = !visibility},
            ){
                if(!visibility){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_visbility_off_foreground),
                        contentDescription = "Visibility_off"
                    )
                }else{
                    Icon(
                        painter = painterResource(id = R.drawable.ic_visbility_foreground),
                        contentDescription = "Visibility_on"
                    )
                }
            }
        }
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
        )
    }
}


//封装了一般的OutlinedTextField
@Composable
fun CommonTextField(
    value : String,
    onValueChange : (String)->Unit,
    labelText : String,
    singleLine: Boolean = true,
    maxLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange =  {onValueChange(it)} ,
        maxLines = maxLines,
        label = {
            Text(text = labelText)
        },
        singleLine = singleLine,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
    )

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCommonTextField(){
    PrivacyKeeperTheme {
        CommonTextField(
            value = "lbw",
            onValueChange = {},
            labelText = "example"
        )
    }
}