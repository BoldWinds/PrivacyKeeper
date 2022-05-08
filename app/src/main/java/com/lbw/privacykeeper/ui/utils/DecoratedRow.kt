package com.lbw.privacykeeper.ui.utils

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import privacykeeperv1.R

@Composable
fun DecoratedRow(
    name : String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
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
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = name[0].toString(),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.weight(0.6f))

        Image(
            painter = painterResource(id = R.drawable.ic_star_foreground),
            contentDescription = "circle",
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier.weight(0.1f)
                .size(28.dp)
        )

        Spacer(modifier = Modifier.weight(0.1f))

    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewDecoratedRow() {
    PrivacyKeeperTheme {
        DecoratedRow(
            name = "Google"
        )
    }
}