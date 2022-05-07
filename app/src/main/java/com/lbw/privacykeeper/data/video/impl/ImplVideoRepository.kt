package com.lbw.privacykeeper.data.video.impl

import android.content.Context
import android.net.Uri
import com.lbw.privacykeeper.data.video.VideoRepository
import com.lbw.privacykeeper.ui.utils.UriType
import com.lbw.privacykeeper.utils.EncryptFromUri
import com.lbw.privacykeeper.utils.Utils
import java.io.File

class ImplVideoRepository(
    context : Context,
    mainKeyAlias : String
):VideoRepository {
    private val encrypt = EncryptFromUri(context = context,mainKeyAlias = mainKeyAlias)

    private val root = File(context.filesDir,"videos")

    private val decrypted = File(root,"decrypted")


    override suspend fun save(uri: Uri, filename: String) {
        encrypt.encryptWrite(uri,filename,UriType.Video)
    }

    override suspend fun read(filename: String): String {
        return encrypt.decrypt(filename,UriType.Video)
    }

    override suspend fun readAll(): List<String> {
        val list = mutableListOf<String>()

        Utils.getAllFileNames(decrypted).forEach{
            list.add(read(it))
        }

        return list
    }


}