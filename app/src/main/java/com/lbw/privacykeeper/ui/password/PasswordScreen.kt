package com.lbw.privacykeeper.ui.password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lbw.privacykeeper.model.Password

@Composable
fun PasswordScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background),
        ){
        PasswordCard(password = Password("1","2","3"))
    }
}