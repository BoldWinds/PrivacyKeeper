package com.lbw.privacykeeper.ui.utils

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

@SuppressLint("RememberReturnType")
@Composable
fun ExoPlayer(
    uri : Uri,
    show : Boolean = true
) {
    if (show){
        val mediaItem : MediaItem = MediaItem.fromUri(uri)
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val context = LocalContext.current

            val exoPlayer = remember(context){
                ExoPlayer.Builder(context).build().apply{
                    val dataSourceFactory : DataSource.Factory = DefaultDataSourceFactory(
                        context, Util.getUserAgent(context,context.packageName)
                    )

                    val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(mediaItem)

                    this.prepare(source)
                }
            }

            AndroidView(
                factory ={context->
                    PlayerView(context).apply {
                        player = exoPlayer
                    }
                }
            )
        }
    }
}