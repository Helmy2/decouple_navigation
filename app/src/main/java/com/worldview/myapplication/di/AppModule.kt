package com.worldview.myapplication.di

import com.worldview.myapplication.navigation.AppNavigator
import com.worldview.myapplication.navigation.NavGraphInstaller
import org.koin.dsl.module

val appModule = module {
    single { AppNavigator() }
    single<List<NavGraphInstaller>> {
        getAll<NavGraphInstaller>()
    }
}
