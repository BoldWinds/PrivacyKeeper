package com.lbw.privacykeeper.ui.image

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme

@Composable
fun ImageScreen(
    imageViewModel: ImageViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Image",
            modifier = Modifier.size(100.dp)
        )
    }
}


@Composable
fun ImageScreen() {

}


@Preview()
@Preview( uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewImageScreen() {
    PrivacyKeeperTheme {
        ImageScreen()
    }
}