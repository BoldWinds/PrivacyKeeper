package com.lbw.privacykeeper

import android.app.Application
import com.lbw.privacykeeper.data.AppContainer
import com.lbw.privacykeeper.data.AppContainerImpl

class PrivacyKeeperApplication : Application(){
    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}