package com.lbw.privacykeeper.ui.password

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbw.privacykeeper.model.Password
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.utils.Utils
import com.lbw.privacykeeper.utils.Utils.Companion.showToast
import privacykeeperv1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordCard(
    password : Password,
) {
    //这个选择状态不要提升到viewModel中,放在这可以最大化简化代码
    var selected by remember {
        mutableStateOf<Boolean>(false)
    }

    var height by remember {
        mutableStateOf(100.dp)
    }


    Card(
        onClick = {
            selected = !selected
            if(height == 100.dp) height = 160.dp
            else    height = 100.dp
        },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier.fillMaxWidth()
            .sizeIn(minWidth = 100.dp,minHeight = height),
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(1.dp,color = MaterialTheme.colorScheme.secondary)
    ) {

        Spacer(modifier = Modifier
            .size(5.dp)
            .fillMaxWidth())
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Spacer(modifier = Modifier.weight(0.05f))

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .weight(0.15f),
                contentAlignment = Alignment.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_circle_foreground),
                    contentDescription = "circle",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary)
                )
                Text(
                    text = password.company[0].toString(),
                    color = MaterialTheme.colorScheme.secondary,
                )
            }

            Spacer(modifier = Modifier.weight(0.6f))

            Image(
                painter = painterResource(id = R.drawable.ic_star_foreground),
                contentDescription = "circle",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier
                    .weight(0.1f)
                    .size(20.dp)
            )

            Spacer(modifier = Modifier.weight(0.1f))

        }

        CommonText(text = stringResource(id = R.string.website)+": "+password.company)

        if (selected){

            CommonText(text = stringResource(id = R.string.username)+": "+password.username)

            CommonText(text = stringResource(id = R.string.password)+": "+password.password)

        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CopyPasswordButton(password = password.password)
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMyCard() {
    PrivacyKeeperTheme {
        PasswordCard(
            password = Password("jetbrains","lbw","LBWNB!"),
        )
    }
}



@Composable
fun CommonText(text : String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            fontSize = 18.sp
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewPasswordText(){
    PrivacyKeeperTheme {
        CommonText(text = "username: LBWNB")
    }
}



@Composable
fun CopyPasswordButton(
    password : String
) {
    val clipboardManager = LocalClipboardManager.current

    val context = LocalContext.current

    OutlinedButton(
        onClick = {
            Utils.clipString(
                clipboardManager = clipboardManager,
                data = password,
            )

            showToast(
                show = true,
                context = context,
                text = context.getString(R.string.has_copied)
            )
        },
        elevation = ButtonDefaults.buttonElevation(2.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)

    ) {
        Text(
            text = stringResource(id = R.string.copy),
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCopyButton(){
    PrivacyKeeperTheme {
        CopyPasswordButton(password = "123")
    }
}

