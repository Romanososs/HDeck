package com.example.hdeck.ui

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.hdeck.auth.AuthService
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.localization.StringProvider
import com.example.hdeck.navigation.Navigator
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class Application: Application(){


    override fun onCreate() {
        super.onCreate()
    }
}