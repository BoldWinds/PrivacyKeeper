package com.lbw.privacykeeper.data.image.impl

import android.content.Context
import android.graphics.BitmapFactory.decodeFile
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.lbw.privacykeeper.data.image.ImageRepository
import com.lbw.privacykeeper.ui.utils.UriType
import com.lbw.privacykeeper.utils.EncryptFromUri
import com.lbw.privacykeeper.utils.Utils.Companion.getAllFileNames
import java.io.File


class ImplImageRepository(
    context : Context,
    mainKeyAlias : String
):ImageRepository {
    private val encrypt = EncryptFromUri(context = context,mainKeyAlias = mainKeyAlias)

    private val root = File(context.filesDir,"images")

    private val decryptedRoot = File(root,"decrypted")

    private val encryptedRoot = File(root,"encrypted")

    //加密保存文件
    override suspend fun save(uri: Uri,filename:String) {
        encrypt.encryptWrite(uri = uri, fileName = filename, uriType = UriType.Image)
    }

    //返回所有加密文件的文件名
    override suspend fun readAllFilenames(): List<String> {
        return getAllFileNames(encryptedRoot)
    }

    //重命名文件
    override suspend fun renameFile(oldFilename: String, newFilename:String) {
        val oldFile = File(encryptedRoot,oldFilename)
        val newFile = File(encryptedRoot,newFilename)
        oldFile.renameTo(newFile)
    }

    //对文件进行解密  并返回解密后的绝对路径
    override suspend fun read(filename: String): String {
        val file = File(decryptedRoot,filename)

        return if(!file.exists()){
            encrypt.decrypt(filename,UriType.Image)
        }else{
            file.absolutePath
        }
    }

    //真正解密文件并得到ImageBitmap
    override suspend fun getImageBitmap(filename: String): ImageBitmap {
        return decodeFile(read(filename)).asImageBitmap()
    }



}