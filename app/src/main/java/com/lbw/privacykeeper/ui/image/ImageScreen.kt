package com.lbw.privacykeeper.ui.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ImageScreen() {
    Box(
        modifier = Modifier.background(MaterialTheme.colors.onBackground)
    ){
        Text(
            text = "Image",
        )
    }
}