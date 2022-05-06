package com.lbw.privacykeeper.data.image.impl

import android.content.Context
import android.graphics.BitmapFactory.decodeFile
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.lbw.privacykeeper.data.image.ImageRepository
import com.lbw.privacykeeper.utils.EncryptImage
import com.lbw.privacykeeper.utils.Utils.Companion.getAllFileNames
import java.io.File

class ImplImageRepository(
    context : Context,
    mainKeyAlias : String
):ImageRepository {
    private val encrypt = EncryptImage(context = context,mainKeyAlias = mainKeyAlias)

    private val root = File(context.filesDir,"images")

    private val decrypted = File(root,"decrypted")

    override suspend fun save(uri: Uri,filename:String) {
        encrypt.encryptWrite(uri = uri, fileName = filename)
    }

    override suspend fun read(filename: String): String {
        return encrypt.decrypt(filename)
    }

    override suspend fun readAll(): List<String> {
        val list = mutableListOf<String>()

        getAllFileNames(decrypted).forEach{
            list.add(read(it))
        }

        return list
    }


    //TODO 总感觉这里内存会炸
    override suspend fun toImageBitmap(): List<ImageBitmap> {
        val list = mutableListOf<ImageBitmap>()

        getAllFileNames(decrypted).forEach{
            list.add(decodeFile(it).asImageBitmap())
        }

        return list
    }

}