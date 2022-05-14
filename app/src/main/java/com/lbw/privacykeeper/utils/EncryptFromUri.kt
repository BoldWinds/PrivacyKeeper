package com.lbw.privacykeeper.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.security.crypto.EncryptedFile
import com.lbw.privacykeeper.model.UriType
import java.io.*


class EncryptFromUri(
    val context : Context,
    val mainKeyAlias : String
) {

    fun decrypt(fileName:String,uriType: UriType):String{
        val rootFile = if (uriType== UriType.Image)  File(context.filesDir,"images")
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


    fun encrypt(uri : Uri, fileName: String, uriType: UriType){
        val rootFile = if (uriType== UriType.Image)  File(context.filesDir,"images")
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

        val fis =  context.contentResolver.openInputStream(uri)
        if (fis==null){
            Log.d("fis","null")
        }else{
            val bufferedInputStream = BufferedInputStream(fis)
            val fos = encryptedFile.openFileOutput()

            var byteArray = ByteArray(512)
            while(bufferedInputStream.read(byteArray)!=-1){
                fos.write(byteArray)
            }

            fis.close()
            bufferedInputStream.close()
            fos.close()
        }

    }

    fun rename(oldName:String,newName:String,uriType: UriType){
        val rootFile = if (uriType== UriType.Image)  File(context.filesDir,"images")
                        else    File(context.filesDir,"videos")
        val encryptedFile = File(File(rootFile,"encrypted"),oldName)
        val decryptedFile = File(decrypt(oldName, uriType))
        encrypt(Uri.fromFile(decryptedFile),newName,uriType)
        encryptedFile.delete()
    }
}