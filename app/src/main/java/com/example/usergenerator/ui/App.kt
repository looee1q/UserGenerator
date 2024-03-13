package com.example.usergenerator.ui

import android.app.Application
import com.example.usergenerator.di.dataModule
import com.example.usergenerator.di.domainModule
import com.example.usergenerator.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}