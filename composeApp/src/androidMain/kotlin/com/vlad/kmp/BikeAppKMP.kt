package com.vlad.kmp

import android.app.Application
import di.PlatformModule
import di.bikesUseCasesModule
import di.localPreferencesModule
import di.repositoriesModule
import di.ridesUseCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BikeAppKMP : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BikeAppKMP)
            androidLogger()
            modules(localPreferencesModule + repositoriesModule + bikesUseCasesModule + ridesUseCasesModule + PlatformModule().modules)

        }
    }
}