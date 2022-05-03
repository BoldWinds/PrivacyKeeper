package com.lbw.privacykeeper.data

import android.content.Context
import com.lbw.privacykeeper.data.password.PasswordRepository
import com.lbw.privacykeeper.data.password.impl.FakePasswordRepository
import com.lbw.privacykeeper.data.preference.PreferenceRepository
import com.lbw.privacykeeper.data.preference.impl.FakePreferenceRepository
import com.lbw.privacykeeper.model.Password
import dagger.hilt.android.qualifiers.ApplicationContext

//实现依赖注入

interface AppContainer{
    val preferenceRepository : PreferenceRepository
    val passwordRepository : PasswordRepository

}

class AppContainerImpl(
    private val applicationContext: Context,
    private val mainKeyAlias : String
):AppContainer {

    override val preferenceRepository : PreferenceRepository by lazy{
        FakePreferenceRepository(context = applicationContext)
    }
    override val passwordRepository: PasswordRepository by lazy{
        FakePasswordRepository(context = applicationContext, mainKeyAlias = mainKeyAlias)
    }

}