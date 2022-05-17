package com.lbw.privacykeeper.ui.user

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import privacykeeperv1.R

@Composable
fun UserBar(
    username : String,
    openConfirmDialog : ()->Unit,
){
    Row(
        modifier = Modifier
            .padding(20.dp, 20.dp, 20.dp, 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Surface(
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_person_foreground),
                contentDescription = "Person",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(modifier = Modifier.weight(0.5f))

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = username,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick =  {
                openConfirmDialog()
            },
            modifier = Modifier.weight(1.2f)
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit_foreground),
                contentDescription = "Setting",
                modifier = Modifier
                    .clip(CircleShape),
                tint = MaterialTheme.colorScheme.secondary
            )
        }

    }

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewUserBar() {
    PrivacyKeeperTheme {
        UserBar(
            username = "lbw",
            openConfirmDialog = {}
        )
    }
}
