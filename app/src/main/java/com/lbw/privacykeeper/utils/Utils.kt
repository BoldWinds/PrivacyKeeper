package com.lbw.privacykeeper.utils

import android.content.Context
import android.widget.Toast
import com.lbw.privacykeeper.model.User


//存放静态方法
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

        fun showToast(
            show : Boolean,
            context: Context,
            text : String
        ) {
            if (show)
                Toast.makeText(context,text, Toast.LENGTH_SHORT).show()
        }


    }

}