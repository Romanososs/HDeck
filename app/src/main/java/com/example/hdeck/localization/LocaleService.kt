package com.example.hdeck.localization

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import com.example.hdeck.data_source.StoreDataSource
import com.example.hdeck.model.Language
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

interface LocaleService {
    val language: Flow<Language>
    fun getString(@StringRes resId: Int): String
    suspend fun getLanguage(): Language
    suspend fun getLocale(): String
    suspend fun setLocale()
    suspend fun setLocale(locale: String)
}

class LocaleServiceImpl @Inject constructor(
    private val context: Context,
    private val store: StoreDataSource,
) : LocaleService {
    override val language: Flow<Language>
        get() = store.locale.map { Language(it) }

    private var resources: Resources = context.resources

    override suspend fun getLanguage(): Language {
        return language.first()
    }

    override suspend fun getLocale(): String {
        return language.map { it.apiCode }.first()
    }

    override suspend fun setLocale() {
        setLocale(getLocale())
    }

    override suspend fun setLocale(locale: String) {
        setResources(locale)
        store.saveLocale(locale)
    }

    override fun getString(@StringRes resId: Int): String {
        return resources.getString(resId)
    }

    private fun setResources(locale: String) {
        val configuration = context.resources.configuration
        configuration.setLocale(Locale(locale))
        resources = context.createConfigurationContext(configuration).resources
    }
}