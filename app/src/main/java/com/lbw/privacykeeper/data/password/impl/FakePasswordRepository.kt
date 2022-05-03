package com.lbw.privacykeeper.data.password.impl

import android.content.Context
import com.lbw.privacykeeper.data.password.PasswordRepository
import com.lbw.privacykeeper.model.Password
import com.lbw.privacykeeper.model.User
import com.lbw.privacykeeper.utils.Encrypt

class FakePasswordRepository(
    context : Context,
    mainKeyAlias : String
) : PasswordRepository{

    private val encrypt = Encrypt(context = context,mainKeyAlias = mainKeyAlias)

    override suspend fun save(password: Password) {
        encrypt.encryptWrite(password = password)
    }

    override suspend fun read(company:String): User{
        return encrypt.encryptRead(company = company)
    }

}