package com.lbw.privacykeeper.ui.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun VideoScreen() {
    Box(
        modifier = Modifier.background(MaterialTheme.colors.onBackground)
    ){
        Text(
            text = "Video",
        )
    }
}