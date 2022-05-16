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
        try {
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
        }catch (e : IOException){
            Log.d("decrypt",e.toString())
            return ""
        }
    }


    fun encrypt(uri : Uri, fileName: String, uriType: UriType){
        try {
            val rootFile = if (uriType== UriType.Image)  File(context.filesDir,"images")
            else    File(context.filesDir,"videos")

            if(!rootFile.exists()){
                rootFile.mkdirs()
            }

            val encryptedRoot = File(rootFile,"encrypted")

            if(!encryptedRoot.exists()){
                encryptedRoot.mkdirs()
            }

            val targetFile = File(encryptedRoot,fileName)
            if (targetFile.exists())    return

            val encryptedFile = EncryptedFile.Builder(
                targetFile,
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
        }catch (e : IOException){
            Log.d("encrypt",e.toString())
        }

    }

    fun rename(oldName:String,newName:String,uriType: UriType){
        try {
            Utils.deleteAllDecrypted(context = context)
            val rootFile = if (uriType== UriType.Image)  File(context.filesDir,"images")
            else    File(context.filesDir,"videos")
            val encryptedRoot = File(rootFile,"encrypted")
            val oldEncryptedFile = EncryptedFile.Builder(
                File(encryptedRoot,oldName),
                context,
                mainKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            val newEncryptedFile = EncryptedFile.Builder(
                File(encryptedRoot,newName),
                context,
                mainKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

            val fis = oldEncryptedFile.openFileInput()
            val bufferedInputStream = BufferedInputStream(fis)
            val fos = newEncryptedFile.openFileOutput()
            val byteArray = ByteArray(1024)
            while (bufferedInputStream.read(byteArray)!=-1){
                fos.write(byteArray)
            }

            fos.close()
            bufferedInputStream.close()
            fis.close()
            //删除旧的加密文件
            File(encryptedRoot,oldName).delete()
        }catch (e:IOException){
            Log.d("rename",e.toString())
        }

    }
}