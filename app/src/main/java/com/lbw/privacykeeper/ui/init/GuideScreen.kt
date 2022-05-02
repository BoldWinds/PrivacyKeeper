package com.lbw.privacykeeper.ui.init

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import privacykeeperv1.R

@Composable
fun GuideScreen(
    showScreen : Boolean = false,
    showRegister : ()->Unit
) {
    if(showScreen){
        Column(
            modifier = Modifier.fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.4f))

            Text(
                text = stringResource(id = R.string.start_text),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Image(
                painter = painterResource(id = R.drawable.ic_lock_fill0_wght400_grad0_opsz48),
                contentDescription = "Lock",
                modifier = Modifier.weight(0.8f),
            //    colorFilter = MaterialTheme.colorScheme.secondary
            )
            
            Button(
                onClick = { showRegister() },
                modifier = Modifier
                    .clip(CircleShape)
                    .weight(0.2f)
                    .fillMaxWidth(0.5f)
            ) {
                Text(text = stringResource(id = R.string.register))
            }

            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewGuideScreen() {
    PrivacyKeeperTheme{
       GuideScreen(true, showRegister = {})
    }
}