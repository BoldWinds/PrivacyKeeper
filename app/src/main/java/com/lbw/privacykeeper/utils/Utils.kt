package com.lbw.privacykeeper.utils


import androidx.compose.ui.platform.ClipboardManager
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.text.AnnotatedString
import com.lbw.privacykeeper.model.User
import com.lbw.privacykeeper.ui.utils.UriType
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
            context: Context,
            text : String
        ) {
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
                if (list.size>0)    list.removeAt(0)
            }
            return list
        }

        //将String复制到剪贴板中
        fun clipString(clipboardManager: ClipboardManager,data : String){
            val annotatedString = AnnotatedString(text = data)
            clipboardManager.setText(annotatedString)
        }

        //在app关闭时删除所有解密的文件
        fun deleteAllDecrypted(context: Context){
            val root = context.filesDir
            //删除已解密文件
            val imageRoot = File(root,"images")
            if (imageRoot.exists()){
                val decryptedRoot = File(imageRoot,"decrypted")
                if (decryptedRoot.exists()){
                    getAllFileNames(decryptedRoot).forEach{
                        File(decryptedRoot,it).delete()
                    }
                }
            }
            //删除以解密视频
            val videoRoot = File(root,"videos")
            if (videoRoot.exists()){
                val decryptedRoot = File(videoRoot,"decrypted")
                if (decryptedRoot.exists()){
                    getAllFileNames(decryptedRoot).forEach{
                        File(decryptedRoot,it).delete()
                    }
                }
            }
        }

        fun getRandomName(uri : Uri,uriType: UriType) : String{
            val str : String = (0..100000).random().toString()
            if(uriType == UriType.Image){
                return "image:$str"
            }else{
                return "video:$str"
            }
            /*Log.d("uri",uri.toString())
            val str = uri.toString()
            val index = str.lastIndexOf("/")
            val str2 =  str.substring(index)
            Log.d("uri",str2)
            return str2.replace("%","0")*/
        }

    }

}