package com.example.hdeck.ui

import android.app.Application
import com.example.hdeck.auth.AuthService
import com.example.hdeck.navigation.Navigator
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Application: Application(){

    override fun onCreate() {
        super.onCreate()
    }
}