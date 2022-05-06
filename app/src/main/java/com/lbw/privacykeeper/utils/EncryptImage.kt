package com.lbw.privacykeeper.utils

import android.content.Context
import android.net.Uri
import androidx.core.net.toFile
import androidx.security.crypto.EncryptedFile
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


//TODO 还未测试
class EncryptImage(
    val context : Context,
    val mainKeyAlias : String
) {

    fun decrypt(fileName:String):Uri{
        val imageFile = File(context.filesDir,"images")

        val file = File(imageFile,"encrypted")

        val encryptedFile = EncryptedFile.Builder(
            File(file,fileName),
            context,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        val inputStream = encryptedFile.openFileInput()
        val byteArrayOutputStream = ByteArrayOutputStream()
        var nextByte: Int = inputStream.read()
        while (nextByte != -1) {
            //这里有可能溢出
            byteArrayOutputStream.write(nextByte)
            nextByte = inputStream.read()
        }

        val decryptedRoot = File(imageFile,"decrypted")
        if(!decryptedRoot.exists()){
            decryptedRoot.mkdirs()
        }

        val decryptedFile = File(decryptedRoot,fileName)
        val fileOutputStream = FileOutputStream(decryptedFile)
        fileOutputStream.apply {
            write(byteArrayOutputStream.toByteArray())
            flush()
            close()
        }

        return  Uri.parse(decryptedFile.path)
    }

    fun encryptWrite(uri : Uri,fileName: String){
        val imageFile = File(context.filesDir,"images")

        if(!imageFile.exists()){
            imageFile.mkdirs()
        }

        val file = File(imageFile,"encrypted")

        if(!file.exists()){
            imageFile.mkdirs()
        }

        val encryptedFile = EncryptedFile.Builder(
            File(file,fileName),
            context,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        //这么做不是很好，一次性读了所有的byte
        val fileContent :ByteArray = uri.toFile().readBytes()

        encryptedFile.openFileOutput().apply {
            write(fileContent)
            flush()
            close()
        }
    }
}