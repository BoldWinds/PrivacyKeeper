package com.lbw.privacykeeper.data.password

import com.lbw.privacykeeper.model.Password

interface PasswordRepository {

    suspend fun save(username:String,password:String)

    suspend fun read() : Password
}