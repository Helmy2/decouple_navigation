package com.worldview.myapplication.di

import com.worldview.myapplication.navigation.AppNavigator
import com.worldview.myapplication.navigation.NavGraphInstaller
import com.worldview.myapplication.navigation.NavigationCommand
import org.koin.dsl.module

val appModule = module {
    single { AppNavigator(NavigationCommand.Home) }
    single<List<NavGraphInstaller>> {
        getAll<NavGraphInstaller>()
    }
}
