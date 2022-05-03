package com.lbw.privacykeeper.data.password.impl

import android.content.Context
import com.lbw.privacykeeper.data.password.PasswordRepository
import com.lbw.privacykeeper.model.Password
import com.lbw.privacykeeper.utils.EncryptPassword
import com.lbw.privacykeeper.utils.Utils

class FakePasswordRepository(
    context : Context,
    mainKeyAlias : String
) : PasswordRepository{

    private val encrypt = EncryptPassword(context = context,mainKeyAlias = mainKeyAlias)

    private val file = context.filesDir

    override suspend fun save(password: Password) {
        encrypt.encryptWrite(password = password)
    }

    override suspend fun read(company:String): Password{
        return encrypt.encryptRead(company = company)
    }

    override suspend fun readAll(): List<Password> {
        val list = mutableListOf<Password>()
        Utils.getAllFileNames(file).forEach {
            list.add(read(it))
        }
        return list
    }

}