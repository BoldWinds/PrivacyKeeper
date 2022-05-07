package com.lbw.privacykeeper.ui.image

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme


//TODO 点击放大全屏
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCard(imageBitmap: ImageBitmap) {
    
    Card(onClick = { /*TODO  放大为全屏*/ }) {
        Image(
            bitmap = imageBitmap,
            contentDescription = "",
            modifier = Modifier.clickable {

            }
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewClickableImage() {
    PrivacyKeeperTheme {
        ImageCard(
            imageBitmap = ImageBitmap(height=1,width=1)
        )
    }
}