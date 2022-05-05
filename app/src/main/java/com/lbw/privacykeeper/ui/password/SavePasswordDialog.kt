package com.lbw.privacykeeper.ui.password

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lbw.privacykeeper.model.Password
import privacykeeperv1.R

@Composable
fun SavePasswordDialog(
    showDialog : Boolean,
    closeDialog : ()->Unit,
    savePassword : (Password)->Unit
) {
    if(showDialog){
        AlertDialog(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            onDismissRequest = {
                closeDialog()
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_warning_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            },
            title = {
                Text(text = stringResource(id = R.string.warning))
            },
            text = {
                Text(
                    text = stringResource(id = R.string.confirm_dialog_text),

                    )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog()
                        savePassword
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