package com.lbw.privacykeeper.data.preference.impl

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.lbw.privacykeeper.data.preference.PreferenceRepository
import kotlinx.coroutines.flow.first

class ImplPreferenceRepository(val context: Context) :PreferenceRepository{

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override suspend fun readBoolean(key:String): Boolean? {
        val BOOLEAN_KEY = booleanPreferencesKey(key)
        val preferences = context.dataStore.data.first()
    //    Log.d("test","has read")
        return preferences[BOOLEAN_KEY]
    }

    //默认存储到BOOT_COUNTER
    override suspend fun saveBoolean(key: String,value: Boolean) {
        val BOOLEAN_KEY = booleanPreferencesKey(key)
        context.dataStore.edit { settings ->
            settings[BOOLEAN_KEY] = value
        }
    //    Log.d("test","$key has saved")
    }

    override suspend fun readString(key: String): String? {
        val STRING_KEY = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
    //    Log.d("test","has read")
        return preferences[STRING_KEY]
    }

    override suspend fun saveString(key: String, value: String) {
        val STRING_KEY = stringPreferencesKey(key)
        context.dataStore.edit { settings ->
            settings[STRING_KEY] = value
        }
    //    Log.d("test","$key has saved")
    }

}