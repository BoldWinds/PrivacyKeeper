package com.lbw.privacykeeper

import android.app.Application
import android.app.KeyguardManager
import androidx.security.crypto.MasterKeys
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.data.AppContainerImpl
import com.lbw.privacykeeper.utils.BiometricCheckParameters

class PrivacyKeeperApplication : Application(){
    lateinit var container : AppContainer
    lateinit var biometricCheckParameters: BiometricCheckParameters

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this, mainKeyAlias = mainKeyAlias)
        biometricCheckParameters = BiometricCheckParameters(
            context = this,
            packageManager = packageManager,
            keyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
        )
    }
}