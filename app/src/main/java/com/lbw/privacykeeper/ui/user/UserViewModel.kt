package com.lbw.privacykeeper.ui.user

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lbw.privacykeeper.data.preference.PreferenceRepository
import com.lbw.privacykeeper.model.User
import com.lbw.privacykeeper.utils.BiometricCheck
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(
    private val preferenceRepository: PreferenceRepository,
    private val biometricCheckParameters: BiometricCheckParameters
):ViewModel() {

    var mutableUser by mutableStateOf<User>(User("",""))

    var showConfirmDialog by mutableStateOf<Boolean>(false)

    fun openConfirmDialog(){
        showConfirmDialog = true
    }

    fun closeConfirmDialog(){
        showConfirmDialog = false
    }

    var permission by mutableStateOf(false)

    fun openBiometricCheck(){
        val biometricCheck = BiometricCheck(
            biometricCheckParameters = biometricCheckParameters,
            onSuccess = { permission = true }
        )
        biometricCheck.launchBiometric()
    }


    var user : User = User("","")

    fun getThisUser(){

        viewModelScope.launch(Dispatchers.IO) {
            val username : String? = preferenceRepository.readString("username")
            val password : String? = preferenceRepository.readString("password")
            if(username!=null&&password!=null)  user = User(username,password)
        }
    }

    fun setUser(){
        mutableUser = user
    }

    fun updatePassword() {}

    companion object{
        fun provideFactory(
            preferenceRepository: PreferenceRepository,
            biometricCheckParameters: BiometricCheckParameters
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return UserViewModel(preferenceRepository, biometricCheckParameters) as T
            }
        }
    }
}