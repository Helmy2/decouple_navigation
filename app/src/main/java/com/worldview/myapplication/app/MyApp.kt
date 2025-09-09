package com.worldview.myapplication.app

import android.app.Application
import com.worldview.myapplication.di.appModule
import com.worldview.myapplication.features.home.homeModule
import com.worldview.myapplication.features.profile.profileModule
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule, homeModule, profileModule)
        }
    }
}

