package com.lbw.privacykeeper.data.password

import com.lbw.privacykeeper.model.Password

interface PasswordRepository {
    suspend fun save(password:Password)

    suspend fun read(company:String) : Password

    suspend fun readAll() : List<Password>
}