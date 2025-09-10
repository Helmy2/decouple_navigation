package com.worldview.myapplication.features.home

import androidx.navigation3.runtime.entry
import com.worldview.myapplication.navigation.NavGraphInstaller
import com.worldview.myapplication.navigation.NavigationCommand
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }

    single<NavGraphInstaller>(named("home"), createdAtStart = true) {
        {
            entry<NavigationCommand.Home> {
                HomeScreen()
            }
            entry<NavigationCommand.Settings> {
                SettingsScreen()
            }
        }
    }
}
