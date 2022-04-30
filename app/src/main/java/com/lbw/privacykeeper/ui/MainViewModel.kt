package com.lbw.privacykeeper.ui


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lbw.privacykeeper.model.User
import java.net.PasswordAuthentication

enum class ThemeMode{
    LightMode,DarkMode
}


class MainViewModel : ViewModel(){

    //用于确定是否显示第一次启动引导界面
    var showGuidance by mutableStateOf<Boolean>(true)

    fun openGuidance(){
        showGuidance = true
    }

    fun closeGuidance(){
        showGuidance = false
    }

    //用于确定是否显示RegisterScreen
    var showRegister by mutableStateOf<Boolean>(false)

    fun openRegisterScreen(){
        showRegister = true
    }

    fun closeRegisterScreen(){
        showRegister = false
    }

    var user by mutableStateOf<User>(User("",""))

    fun setUsername(username : String){
        user.username = username
    }

    fun setPassword(password : String){
        user.password = password
    }

    fun saveUser(){

    }


    //用于确定何时显示MainScreen
    var showMain by mutableStateOf(false)

    fun openMain(){
        showMain = true
    }

    //用于调节右上角的图片是lightMode还是darkMode
    var themeMode by mutableStateOf(ThemeMode.LightMode)

    fun setLightMode(){
        themeMode = ThemeMode.LightMode
    }

    fun setDarkMode(){
        themeMode = ThemeMode.DarkMode
    }

    fun isLight():Boolean{
        return themeMode == ThemeMode.LightMode
    }


}