package com.lbw.privacykeeper.data.image

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap

interface ImageRepository {
    suspend fun save(uri:Uri,filename:String)

    suspend fun read(filename: String) : String

    suspend fun readAll() : List<String>

    suspend fun toImageBitmap() : List<ImageBitmap>

    suspend fun toImageBitmap(filename: String):ImageBitmap
}