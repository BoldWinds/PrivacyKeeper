package com.lbw.privacykeeper.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.security.crypto.EncryptedFile
import java.io.*


class EncryptImage(
    val context : Context,
    val mainKeyAlias : String
) {

    fun decrypt(fileName:String):String{
        val imageFile = File(context.filesDir,"images")

        val file = File(imageFile,"encrypted")

        val decryptedRoot = File(imageFile,"decrypted")
        if(!decryptedRoot.exists()){
            decryptedRoot.mkdirs()
        }

        val decryptedFile = File(decryptedRoot,fileName)

        val encryptedFile = EncryptedFile.Builder(
            File(file,fileName),
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

        return file.absolutePath
    }


    fun encryptWrite(uri : Uri,fileName: String){
        val imageFile = File(context.filesDir,"images")

        if(!imageFile.exists()){
            imageFile.mkdirs()
        }

        val file = File(imageFile,"encrypted")

        if(!file.exists()){
            file.mkdirs()
        }


        val encryptedFile = EncryptedFile.Builder(
            File(file,fileName),
            context,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        val copyFile = File(file,fileName)

        val fis =  FileInputStream(context.contentResolver.openFileDescriptor(uri,"r")?.fileDescriptor)
        fis.copyTo(FileOutputStream(copyFile.absoluteFile))
        fis.close()

        val fileContent :ByteArray = copyFile.readBytes()
        Log.d("path",copyFile.absolutePath)

        copyFile.delete()

        encryptedFile.openFileOutput().apply {
            write(fileContent)
            flush()
            close()
        }
    }
}