package com.lbw.privacykeeper.data.password.impl

import com.lbw.privacykeeper.data.password.PasswordRepository
import com.lbw.privacykeeper.model.Password

class FakePasswordRepository : PasswordRepository{

    override suspend fun save(username: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun read(): Password {
       // TODO("Not yet implemented")
        return Password("JetBrain","834264619@qq.com","123456")
    }

}