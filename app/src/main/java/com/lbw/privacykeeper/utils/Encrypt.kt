package com.lbw.privacykeeper.utils

import android.content.Context
import android.util.Log
import androidx.security.crypto.EncryptedFile
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets

class Encrypt(
    val context : Context,
    val mainKeyAlias : String
) {

    fun encryptRead(filePath : String){

        val encryptedFile = EncryptedFile.Builder(
            File(context.filesDir,filePath),
            context,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        val inputStream = encryptedFile.openFileInput()
        val byteArrayOutputStream = ByteArrayOutputStream()
        var nextByte: Int = inputStream.read()
        while (nextByte != -1) {
            byteArrayOutputStream.write(nextByte)
            nextByte = inputStream.read()
        }

        val plaintext: ByteArray = byteArrayOutputStream.toByteArray()
        Log.d("read",plaintext.decodeToString())
    }

    fun encryptWrite(fileToWrite: String){
        val encryptedFile = EncryptedFile.Builder(
            File(context.filesDir,fileToWrite),
            context,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        val fileContent = "MY SUPER-SECRET INFORMATION"
            .toByteArray(StandardCharsets.UTF_8)
        encryptedFile.openFileOutput().apply {
            write(fileContent)
            flush()
            close()
        }
    }
}