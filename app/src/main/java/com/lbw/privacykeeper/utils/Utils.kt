package com.lbw.privacykeeper.utils


import androidx.compose.ui.platform.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.text.AnnotatedString
import com.lbw.privacykeeper.model.User
import java.io.File


//存放静态方法
class Utils {

    companion object{

        //将String类型转换成User类型
        fun StringToUser(str : String):User{
            val username : String
            val password : String

            val index = str.indexOf(' ')
            username = str.substring(0,index)
            password = str.substring(index+1)

            return User(username = username, password = password)
        }

        //封装Toast
        fun showToast(
            show : Boolean,
            context: Context,
            text : String
        ) {
            if (show)
                Toast.makeText(context,text, Toast.LENGTH_SHORT).show()
        }

        //获取file这个目录下所有的文件名
        fun getAllFileNames(file:File?):List<String>{
            val list = mutableListOf<String>()

            if (file==null) {
                Log.d("test","file error")
            }else{
                val fileTreeWalk : FileTreeWalk = file.walk()
                fileTreeWalk
                    .forEach {
                        list.add(it.name)
                    }
                list.removeAt(0)
            }
            return list
        }

        //将String复制到剪贴板中
        fun clipString(clipboardManager: ClipboardManager,data : String){
            val annotatedString = AnnotatedString(text = data)
            clipboardManager.setText(annotatedString)
        }

    }

}