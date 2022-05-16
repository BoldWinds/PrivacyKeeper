package com.lbw.privacykeeper.ui.user


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lbw.privacykeeper.data.preference.PreferenceRepository
import com.lbw.privacykeeper.model.User
import com.lbw.privacykeeper.utils.BiometricCheck
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import kotlinx.coroutines.launch


class UserViewModel(
    private val preferenceRepository: PreferenceRepository,
    private val biometricCheckParameters: BiometricCheckParameters
):ViewModel() {

    var permission by mutableStateOf(false)

    fun closeUpdateDialog(){
        permission = false
    }

    fun openBiometricCheck(){
        val biometricCheck = BiometricCheck(
            biometricCheckParameters = biometricCheckParameters,
            onSuccess = {
                onSuccess()
            },
            openPermissionDialog = {
                openPermissionDialog()
            }
        )
        biometricCheck.launchBiometric()
    }

    var showPermissionDialog by mutableStateOf(false)

    fun openPermissionDialog(){
        showPermissionDialog = true
    }

    fun closePermissionDialog(){
        showPermissionDialog = false
    }

    fun checkPermission(password: String){
        if (password == user.password){
            onSuccess()
        }
    }

    fun onSuccess(){
        permission = true
    }

    var user by mutableStateOf(User("",""))

    @JvmName("setUser1")
    fun setUser(u : User){
        user = u
    }


    fun updateUser(password: String){
        viewModelScope.launch {
            preferenceRepository.saveString("password",password)
        }
    }

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