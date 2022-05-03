package com.lbw.privacykeeper.utils

import android.content.Context
import androidx.security.crypto.EncryptedFile
import com.lbw.privacykeeper.model.Password
import com.lbw.privacykeeper.model.User
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets

class EncryptPassword(
    val context : Context,
    val mainKeyAlias : String
) {

    fun encryptRead(company : String):User{

        val encryptedFile = EncryptedFile.Builder(
            File(context.filesDir,company),
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
        byteArrayOutputStream.close()

        return Utils.StringToUser(plaintext.toString(StandardCharsets.UTF_8))
    }

    fun encryptWrite(password : Password){
        val encryptedFile = EncryptedFile.Builder(
            File(context.filesDir,password.company),
            context,
            mainKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        //用空格做区分用户名与密码
        val fileContent = "${password.username} ${password.password}"
            .toByteArray(StandardCharsets.UTF_8)
        encryptedFile.openFileOutput().apply {
            write(fileContent)
            flush()
            close()
        }
    }
}