package com.lbw.privacykeeper.ui.password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lbw.privacykeeper.data.password.PasswordRepository
import com.lbw.privacykeeper.model.Password
import com.lbw.privacykeeper.model.User
import com.lbw.privacykeeper.ui.nav.AppSecondaryScreen
import com.lbw.privacykeeper.utils.BiometricCheck
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordViewModel(
    private val passwordRepository: PasswordRepository,
    private val biometricCheckParameters: BiometricCheckParameters,
    private val navController: NavHostController
) : ViewModel(){

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
        navController.navigate(AppSecondaryScreen.Password.route)
    }

    var user by mutableStateOf(User("",""))

    @JvmName("setUser1")
    fun setUser(u : User){
        user = u
    }

    var showSavePasswordDialog by mutableStateOf(false)

    fun openDialog(){
        showSavePasswordDialog = true
    }

    fun closeDialog(){
        showSavePasswordDialog = false
    }

    fun savePassword(password : Password){
        viewModelScope.launch(Dispatchers.IO) {
            passwordRepository.save(password = password)
        }
    }


    var passwordList : List<Password> = mutableListOf<Password>()

    fun readAllPassword(){
        viewModelScope.launch(Dispatchers.IO) {
            passwordList = passwordRepository.readAll()
        }
    }

    fun delete(name : String){
        viewModelScope.launch {
            passwordRepository.delete(name)
            readAllPassword()
        }
    }

    companion object{
        fun provideFactory(
            passwordRepository: PasswordRepository,
            biometricCheckParameters: BiometricCheckParameters,
            navController: NavHostController
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return PasswordViewModel(passwordRepository, biometricCheckParameters,navController) as T
            }
        }
    }
}

