package com.lbw.privacykeeper.data.image

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap

interface ImageRepository {
    suspend fun save(uri:Uri,filename:String)

    suspend fun read(filename: String) : String

    suspend fun readAllFilenames() : List<String>

    suspend fun renameFile(oldFilename: String,newFilename:String)

    suspend fun getImageBitmap(filename: String):ImageBitmap

    suspend fun delete(filename: String)
}