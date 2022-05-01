package com.lbw.privacykeeper.ui


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class ThemeMode{
    LightMode,DarkMode
}


class MainViewModel : ViewModel(){

    //用于确定是否显示第一次启动引导界面
    var showGuidance by mutableStateOf<Boolean>(false)

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



    //存储用户
    fun saveUser(username: String,password: String){
        Log.d("username",username)
        Log.d("password",password)
    }


}