package com.lbw.privacykeeper.data.image

import android.net.Uri

interface ImageRepository {
    suspend fun save(uri:Uri,filename:String)

    suspend fun read() : Uri

    suspend fun readAll() : List<Uri>
}