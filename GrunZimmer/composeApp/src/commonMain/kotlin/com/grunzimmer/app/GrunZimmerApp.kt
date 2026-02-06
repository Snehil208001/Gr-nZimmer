package com.grunzimmer.app

import android.app.Application
import com.grunzimmer.app.di.initKoin
import org.koin.android.ext.koin.androidContext

class GrunZimmerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@GrunZimmerApp)
        }
    }
}