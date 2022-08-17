package com.example.hdeck.service

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import com.example.hdeck.data_source.StoreDataSource
import java.util.*
import javax.inject.Inject

interface LocalizationService {
    fun setLocale(c: Context, locale: String): Context
}

class LocalizationServiceImpl @Inject constructor(
    private val store: StoreDataSource,
) : LocalizationService {
    override fun setLocale(c: Context, locale: String): Context {
        var context = c
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        configuration.setLocale(Locale(locale))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
            context = context.createConfigurationContext(configuration)
        else
            resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }

}
