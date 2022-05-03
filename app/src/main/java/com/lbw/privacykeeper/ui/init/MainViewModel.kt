package com.lbw.privacykeeper.ui.init


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lbw.privacykeeper.data.preference.PreferenceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class ThemeMode{
    LightMode,DarkMode
}


class MainViewModel(
    private val preferenceRepository: PreferenceRepository,
) : ViewModel(){

    //用于确定是否显示第一次启动引导界面
    var showGuidance by mutableStateOf<Boolean>(false)

    fun setShowGuidance(){
        viewModelScope.launch(Dispatchers.IO) {
            val show = preferenceRepository.readBoolean("boot_counter")
            if(show==null){
                showGuidance = true
                Log.d("test","save")
                preferenceRepository.saveBoolean("boot_counter",true)
            }else{
                showGuidance = show
                showMain = !show
                Log.d("test",show.toString())
            }
            Log.d("test","finish")
        }
    }

    //用于确定是否显示RegisterScreen
    var showRegister by mutableStateOf<Boolean>(false)

    fun openRegisterScreen(){
        showRegister = true
    }

    fun hasRegistered(){
        viewModelScope.launch(Dispatchers.IO) {
            preferenceRepository.saveBoolean("boot_counter",false)
            Log.d("test","hasRegistered")
        }
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
        viewModelScope.launch {
            //TODO 加密密码
            preferenceRepository.saveString("username",username)
            preferenceRepository.saveString("password",password)
            delay(2000)
        }
    }

    companion object{
        fun provideFactory(
            preferenceRepository: PreferenceRepository,
        ):ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return MainViewModel(preferenceRepository) as T
            }
        }
    }


}