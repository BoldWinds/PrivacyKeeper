package com.lbw.privacykeeper.ui.image

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.ui.utils.GalleryButton
import com.lbw.privacykeeper.ui.utils.UriType

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
        GalleryButton(setUri = imageViewModel::setNewUri, save = imageViewModel::saveImage, uriType = UriType.Image)
    }
}


@Composable
fun ImageScreen() {

}


@Preview
@Preview( uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewImageScreen() {
    PrivacyKeeperTheme {
        ImageScreen()
    }
}