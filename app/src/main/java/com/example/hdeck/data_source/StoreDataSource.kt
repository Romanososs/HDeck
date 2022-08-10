package com.example.hdeck.data_source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.hdeck.auth.AuthData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface StoreDataSource {
    val accessToken: Flow<AuthData?>
    suspend fun saveToken(accessToken: String)
    suspend fun clear()
}

class StoreDataSourceImpl @Inject constructor(
    private val context: Context
): StoreDataSource {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override val accessToken: Flow<AuthData?>
        get() = context.dataStore.data.map { preferences ->
            AuthData(
                preferences[ACCESS_TOKEN],
                preferences[EXPIRES]
            )
        }


    override suspend fun saveToken(accessToken: String) {
        context.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("key_access_token")
        private val EXPIRES = longPreferencesKey("key_expires")
    }
}