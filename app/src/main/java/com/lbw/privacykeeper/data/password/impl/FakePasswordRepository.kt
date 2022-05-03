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
        encrypt.encryptWrite()
    }

    override suspend fun read(company:String): User{
       // TODO("Not yet implemented")
        return User("834264619@qq.com","123456")
    }

}