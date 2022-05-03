package com.lbw.privacykeeper.utils

import com.lbw.privacykeeper.model.User

class Utils {
    companion object{
        fun StringToUser(str : String):User{
            val username : String
            val password : String

            val index = str.indexOf(' ')
            username = str.substring(0,index)
            password = str.substring(index+1)

            return User(username = username, password = password)
        }
    }
}