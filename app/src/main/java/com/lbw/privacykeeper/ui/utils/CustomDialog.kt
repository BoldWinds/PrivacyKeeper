package com.lbw.privacykeeper.ui.utils

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import privacykeeperv1.R


//给Dialog提升代码复用性
@Composable
fun CustomDialog(
    showDialog : Boolean,
    title : String,
    closeDialog: ()->Unit,
    onConfirm : ()->Unit,
    onDismiss : ()->Unit,
    content : @Composable ()->Unit
) {

    if (showDialog){
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
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            text = {
                content()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm()
                        closeDialog()
                    }
                ) {
                    Text(text = stringResource(id = R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
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