package com.lbw.privacykeeper

import android.app.Application
import android.app.KeyguardManager
import android.database.ContentObserver
import android.util.Log
import androidx.security.crypto.MasterKeys
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.data.ImplAppContainer
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import com.lbw.privacykeeper.utils.Utils

class PrivacyKeeperApplication : Application(){
    lateinit var container : AppContainer
    lateinit var biometricCheckParameters: BiometricCheckParameters

    override fun onCreate() {
        super.onCreate()
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
        container = ImplAppContainer(this, mainKeyAlias = mainKeyAlias)
        biometricCheckParameters = BiometricCheckParameters(
            context = this,
            packageManager = packageManager,
            keyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
        )
    }
}