package com.lbw.privacykeeper.ui.user

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import privacykeeperv1.R

@Composable
fun UserBar(
    userName : String,
    updatePassword : @Composable ()->Unit) {
    Row(
        modifier = Modifier
            .padding(24.dp, 24.dp, 24.dp, 24.dp)
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

        Spacer(modifier = Modifier.size(40.dp))

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Welcome Back!",
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = userName,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.size(40.dp))

        IconButton(
            onClick = { updatePassword }
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.ic_setting_foreground),
                contentDescription = "Setting",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp),
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
        UserBar(userName = "LBW",{})
    }
}