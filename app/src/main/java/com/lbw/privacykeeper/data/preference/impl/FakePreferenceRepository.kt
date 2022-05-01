package com.lbw.privacykeeper.data.preference.impl

import android.content.Context
import androidx.datastore.core.DataStore
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

    override suspend fun read(): Boolean {
        val exampleCounterFlow: Flow<Boolean> = context.dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[BOOT_COUNTER] ?: true
            }
        return exampleCounterFlow.first()
    }

    //默认存储到BOOT_COUNTER
    override suspend fun save(value: Boolean) {
        context.dataStore.edit { settings ->
            settings[BOOT_COUNTER] = value
        }
    }

}