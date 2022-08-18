package com.example.hdeck.localization

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.annotation.StringRes
import java.util.*
import javax.inject.Inject

class StringProvider @Inject constructor(
    private val context: Context
) {
    private lateinit var resources: Resources

    fun setLocale(locale: String) {
        val configuration = context.resources.configuration
        configuration.setLocale(Locale(locale))
        resources = context.createConfigurationContext(configuration).resources
    }

    fun getString(@StringRes resId: Int): String {
        return resources.getString(resId)
    }
}