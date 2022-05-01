package com.lbw.privacykeeper.data.preference.impl

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.lbw.privacykeeper.data.preference.PreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class FakePreferenceRepository(val context: Context) :PreferenceRepository{

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val BOOT_COUNTER = booleanPreferencesKey("boot_counter")

    override suspend fun read(): Boolean? {
        val preferences = context.dataStore.data.first()
        Log.d("test","has read")
        return preferences[BOOT_COUNTER]
    }

    //默认存储到BOOT_COUNTER
    override suspend fun save(value: Boolean) {
        context.dataStore.edit { settings ->
            settings[BOOT_COUNTER] = value
        }
        Log.d("test","has saved")
    }

}