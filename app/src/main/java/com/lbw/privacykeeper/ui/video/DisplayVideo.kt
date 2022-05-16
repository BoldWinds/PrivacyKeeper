package com.lbw.privacykeeper.ui.video

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.ui.utils.ExoPlayer
import com.lbw.privacykeeper.ui.utils.LoadingAnimation

@Composable
fun DisplayVideo(
    showPlayer : Boolean = false,
    uri : Uri,
    openVideo : ()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f),
        contentAlignment = Alignment.Center
    ){
        ExoPlayer(
            uri = uri,
            show = showPlayer,
            openVideo = openVideo
        )
        LoadingAnimation(show = !showPlayer)
    }
}

@Preview
@Composable
fun PreviewDisplayVideo() {
    PrivacyKeeperTheme {
        DisplayVideo(
            uri = Uri.EMPTY,
            openVideo = {}
        )
    }
}