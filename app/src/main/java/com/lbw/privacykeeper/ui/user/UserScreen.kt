package com.lbw.privacykeeper.ui.user

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.ui.utils.PermissionDialog
import com.lbw.privacykeeper.ui.utils.UpdateDialog
import privacykeeperv1.R


@Composable
fun UserScreen(
    username : String = "lbw",
    onBiometricCheck: ()->Unit={},
    showUpdateDialog : Boolean = false,
    closeUpdateDialog:()->Unit = {},
    savePassword : (String)->Unit = {},
    showPermissionDialog : Boolean = false,
    closePermissionDialog : ()->Unit = {},
    checkPermission : (String)->Unit = {},
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),

        ) {

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .size(15.dp))

        UserBar(
            username = username,
            openConfirmDialog = onBiometricCheck,
        )
        
        Spacer(modifier = Modifier.size(30.dp))

        GuideBar()

        Spacer(modifier = Modifier.weight(0.2f))

    }

    PermissionDialog(
        showPermissionDialog = showPermissionDialog,
        closePermissionDialog = closePermissionDialog,
        checkPermission = checkPermission
    )

    UpdateDialog(
        showDialog = showUpdateDialog,
        closeDialog = closeUpdateDialog,
        savePassword = savePassword
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewUserScreen() {
    PrivacyKeeperTheme {
        UserScreen()
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideBar() {
    var height by remember {
        mutableStateOf(50.dp)
    }

    var text by remember {
        mutableStateOf(R.string.press_more)
    }

    var icon by remember {
        mutableStateOf(R.drawable.ic_more_foreground)
    }

    ElevatedCard(
        onClick = {
            if(height == 50.dp){
                height = 300.dp
                text = R.string.press_less
                icon = R.drawable.ic_less_foreground
            }
            else{
                height = 50.dp
                text = R.string.press_more
                icon = R.drawable.ic_more_foreground
            }
        },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(10.dp, 5.dp, 10.dp, 5.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){

            Spacer(modifier = Modifier.weight(0.1f))

            Text(
                text = stringResource(id = text),
                modifier = Modifier.weight(0.6f),
                maxLines = 1,
                color = MaterialTheme.colorScheme.onPrimary,
            )

            Icon(
                painter = painterResource(id = icon),
                contentDescription = "right",
                modifier = Modifier.weight(0.2f)
            )

        }

        if (height!=50.dp){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp, 15.dp, 0.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = stringResource(id = R.string.instruction1),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic
                )
                
                Spacer(modifier = Modifier.size(15.dp))
                
                Text(
                    text = stringResource(id = R.string.instruction2),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic
                )

                Spacer(modifier = Modifier.size(15.dp))

                Text(
                    text = stringResource(id = R.string.instruction3),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }

    }


}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewGuideBar() {
    PrivacyKeeperTheme {
        GuideBar()
    }
}
