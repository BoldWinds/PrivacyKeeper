package com.lbw.privacykeeper.utils

import android.content.Context
import android.net.Uri
import androidx.security.crypto.EncryptedFile
import com.lbw.privacykeeper.ui.utils.UriType
import java.io.*


class EncryptFromUri(
    val context : Context,
    val mainKeyAlias : String
) {

    fun decrypt(fileName:String,uriType: UriType):String{
        val rootFile = if (uriType==UriType.Image)  File(context.filesDir,"images")
                        else    File(context.filesDir,"videos")

        val encryptedRoot = File(rootFile,"encrypted")

        val decryptedRoot = File(rootFile,"decrypted")
        if(!decryptedRoot.exists()){
            decryptedRoot.mkdirs()
        }

        val decryptedFile = File(decryptedRoot,fileName)

        val encryptedFile = EncryptedFile.Builder(
            File(encryptedRoot,fileName),
            context,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        val inputStream = encryptedFile.openFileInput()
        val bufferedInputStream = BufferedInputStream(inputStream)
        val fileOutputStream = FileOutputStream(decryptedFile)
        var byteArray = ByteArray(1024)
        while(bufferedInputStream.read(byteArray)!=-1){
            fileOutputStream.write(byteArray)
        }
        fileOutputStream.close()

        return decryptedFile.absolutePath
    }


    fun encryptWrite(uri : Uri,fileName: String,uriType: UriType){
        val rootFile = if (uriType==UriType.Image)  File(context.filesDir,"images")
                        else    File(context.filesDir,"videos")

        if(!rootFile.exists()){
            rootFile.mkdirs()
        }

        val encryptedRoot = File(rootFile,"encrypted")

        if(!encryptedRoot.exists()){
            encryptedRoot.mkdirs()
        }


        val encryptedFile = EncryptedFile.Builder(
            File(encryptedRoot,fileName),
            context,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        val copyFile = File(encryptedRoot,fileName)

        val fis =  FileInputStream(context.contentResolver.openFileDescriptor(uri,"r")?.fileDescriptor)
        fis.copyTo(FileOutputStream(copyFile.absoluteFile))
        fis.close()

        val fileContent :ByteArray = copyFile.readBytes()

    //    Log.d("path",copyFile.absolutePath)

        copyFile.delete()

        encryptedFile.openFileOutput().apply {
            write(fileContent)
            flush()
            close()
        }
    }
}