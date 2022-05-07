package com.lbw.privacykeeper.data.video

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap

interface VideoRepository {
    suspend fun save(uri: Uri, filename:String)

    suspend fun read(filename: String) : String

    suspend fun readAll() : List<String>

}