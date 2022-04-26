package com.lbw.privacykeeper.ui.user

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.lbw.privacykeeper.model.User

data class UIState(
    var user : User = User("Lbw","123456"),
    var showConfirmDialog : Boolean = false
)

class UserViewModel():ViewModel() {

    var user by mutableStateOf<User>(User("",""))

    var showConfirmDialog by mutableStateOf<Boolean>(false)

    fun openConfirmDialog(){
        showConfirmDialog = true
    }

    fun closeConfirmDialog(){
        showConfirmDialog = false
    }

    fun updatePassword() {}
}