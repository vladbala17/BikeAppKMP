package com.vlad.kmp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BikeAppKMP: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BikeAppKMP)
            androidLogger()
            modules()
        }
    }
}