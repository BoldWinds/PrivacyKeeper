package com.lbw.privacykeeper.utils

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbw.privacykeeper.model.Password
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
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

    val interactionSource = remember { MutableInteractionSource() }

    /*val clickable = Modifier.clickable(
        interactionSource = interactionSource,
        indication = LocalIndication.current
    ){
        selected = !selected
    }

    val isPressed by interactionSource.collectIsPressedAsState()*/

    Card(
        onClick = { selected = !selected },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier.size(width = 180.dp,height = 100.dp),
        interactionSource = interactionSource,
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

        Text(
            text = password.company,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        CopyPasswordButton(password = password.password)

        if (selected){

            Text(
                text = password.username,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = password.password,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

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
            password = Password("我","lbw","LBWNB!"),
        )
    }
}