package com.gcuestab.mynotificationapplication

import android.app.Application
import com.gcuestab.mynotificationapplication.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(appModule)
        }
    }
}