package com.lbw.privacykeeper.data.password

import com.lbw.privacykeeper.model.Password
import com.lbw.privacykeeper.model.User

interface PasswordRepository {

    suspend fun save(password:Password)

    suspend fun read(company:String) : User
}