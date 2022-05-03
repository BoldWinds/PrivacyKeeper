package com.lbw.privacykeeper

import android.app.Application
import androidx.security.crypto.MasterKeys
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.data.AppContainerImpl

class PrivacyKeeperApplication : Application(){
    lateinit var container : AppContainer

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this, mainKeyAlias = mainKeyAlias)
    }
}