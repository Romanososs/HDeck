package com.example.hdeck.localization

import com.example.hdeck.data_source.StoreDataSource
import com.example.hdeck.model.Language
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface LocaleService {
    val language: Flow<Language>
    suspend fun getLanguage(): Language
    suspend fun getApiLocale(): String
    suspend fun getLocale(): String
    suspend fun setLocale(locale: String)
}

class LocaleServiceImpl @Inject constructor(
    private val store: StoreDataSource,
) : LocaleService {
    override val language: Flow<Language>
        get() = store.locale.map { Language(it) }

    override suspend fun getLanguage(): Language {
        return language.first()
    }

    override suspend fun getApiLocale(): String {
        return language.map { it.apiCode }.first()
    }

    override suspend fun getLocale(): String {
        return language.map { it.code }.first()
    }

    override suspend fun setLocale(locale: String) {
        store.saveLocale(locale)
    }

}