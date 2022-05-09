package com.lbw.privacykeeper.ui.utils

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import privacykeeperv1.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageOrVideoCard(
    filename:String,
    openImage : (String)->Unit,
    setOldName : (String)->Unit,
    openDialog : ()->Unit,
    delete : (String)->Unit
) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    CustomDialog(
        showDialog =showDialog ,
        title = stringResource(id = R.string.delete),
        closeDialog = { showDialog = false },
        onConfirm = { delete(filename)},
        onDismiss = {}
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = stringResource(id = R.string.confirm_delete),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            )
        }
    }


    Card(
        onClick = {
            openImage(filename)
        },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(1.dp,color = MaterialTheme.colorScheme.secondary)
    ){
        Spacer(modifier = Modifier
            .size(10.dp)
            .fillMaxWidth())
        
        DecoratedRow(name = filename)
        
        CommonText(text = filename)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){

            Button(
                onClick = {
                    setOldName(filename)
                    openDialog()
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = stringResource(id = R.string.rename),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

            Spacer(modifier = Modifier.size(10.dp))

            Button(
                onClick = {
                    showDialog = true
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = stringResource(id = R.string.delete),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

        }

    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewClickableImage() {
    PrivacyKeeperTheme {
        ImageOrVideoCard(
            filename = "MyImage",
            openImage = {},
            setOldName = {},
            openDialog = {},
            delete = {}
        )
    }
}