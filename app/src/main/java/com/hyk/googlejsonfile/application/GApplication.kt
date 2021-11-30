package com.hyk.googlejsonfile.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GApplication : Application() {

    @Suppress("unused")
    override fun onCreate() {
        super.onCreate()
    }
}