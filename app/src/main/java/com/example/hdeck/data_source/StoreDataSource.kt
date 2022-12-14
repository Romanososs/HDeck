package com.example.hdeck.data_source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.hdeck.auth.AuthData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface StoreDataSource {
    val authData: Flow<AuthData?>
    val locale: Flow<String>
    suspend fun saveToken(accessToken: String, expiresIn: Long)
    suspend fun saveLocale(locale: String)
    suspend fun clear()
}

class StoreDataSourceImpl @Inject constructor(
    private val context: Context
) : StoreDataSource {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override val authData: Flow<AuthData?>
        get() = context.dataStore.data.map { preferences ->
            val accessToken = preferences[ACCESS_TOKEN]
            val expiresIn = preferences[EXPIRES]
            if (accessToken != null && expiresIn != null)
                AuthData(
                    accessToken,
                    expiresIn
                )
            else null
        }
    override val locale: Flow<String>
        get() = context.dataStore.data.map { preferences ->
            preferences[LOCALE] ?: "en"
        }


    override suspend fun saveToken(accessToken: String, expiresIn: Long) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                preferences[ACCESS_TOKEN] = accessToken
                preferences[EXPIRES] = expiresIn
            }
        }
    }
    override suspend fun saveLocale(locale: String) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                preferences[LOCALE] = locale
            }
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
        private val LOCALE = stringPreferencesKey("key_locale")
    }
}