package com.lbw.privacykeeper.data

import android.content.Context
import com.lbw.privacykeeper.data.preference.PreferenceRepository
import com.lbw.privacykeeper.data.preference.impl.FakePreferenceRepository
import dagger.hilt.android.qualifiers.ApplicationContext

interface AppContainer{
    val preferenceRepository : PreferenceRepository
}

class AppContainerImpl(private val applicationContext: Context):AppContainer {

    override val preferenceRepository : PreferenceRepository by lazy{
        FakePreferenceRepository(context = applicationContext)
    }
}