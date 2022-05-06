package com.lbw.privacykeeper.data.image.impl

import android.content.Context
import android.net.Uri
import com.lbw.privacykeeper.data.image.ImageRepository
import com.lbw.privacykeeper.utils.EncryptImage
import java.io.File

class ImplImageRepository(
    context : Context,
    mainKeyAlias : String
):ImageRepository {
    private val encrypt = EncryptImage(context = context,mainKeyAlias = mainKeyAlias)

    private val file = File(context.filesDir,"images")

    override suspend fun save(uri: Uri,filename:String) {
        encrypt.encryptWrite(uri = uri, fileName = filename)
    }

    override suspend fun read(): Uri {
        TODO("Not yet implemented")
    }

    override suspend fun readAll(): List<Uri> {
        TODO("Not yet implemented")
    }

}