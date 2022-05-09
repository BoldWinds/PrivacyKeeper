package com.lbw.privacykeeper.data.video.impl

import android.content.Context
import android.net.Uri
import com.lbw.privacykeeper.data.video.VideoRepository
import com.lbw.privacykeeper.ui.utils.UriType
import com.lbw.privacykeeper.utils.EncryptFromUri
import com.lbw.privacykeeper.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File


class ImplVideoRepository(
    context : Context,
    mainKeyAlias : String
):VideoRepository {
    private val encrypt = EncryptFromUri(context = context,mainKeyAlias = mainKeyAlias)

    private val root = File(context.filesDir,"videos")

    private val decryptedRoot = File(root,"decrypted")

    private val encryptedRoot = File(root,"encrypted")


    //加密保存文件
    override suspend fun save(uri: Uri,filename:String) {
        return withContext(Dispatchers.IO){
            encrypt.encrypt(uri = uri, fileName = filename, uriType = UriType.Video)
        }
    }

    //返回所有加密文件的文件名
    override suspend fun readAllFilenames(): List<String> {
        return withContext(Dispatchers.IO){
            Utils.getAllFileNames(encryptedRoot)
        }
    }

    //重命名文件
    override suspend fun renameFile(oldFilename: String, newFilename:String) {
        return withContext(Dispatchers.IO){
            encrypt.rename(oldFilename,newFilename,UriType.Video)
        }
    }

    override suspend fun delete(filename: String) {
        return withContext(Dispatchers.IO){
            File(encryptedRoot,filename).delete()
        }
    }

    //对文件进行解密  并返回解密后的绝对路径
    override suspend fun read(filename: String): String {
        return withContext(Dispatchers.IO){
            val file = File(decryptedRoot,filename)

            if(!file.exists()){
                encrypt.decrypt(filename,UriType.Video)
            }else{
                file.absolutePath
            }
        }
    }


}