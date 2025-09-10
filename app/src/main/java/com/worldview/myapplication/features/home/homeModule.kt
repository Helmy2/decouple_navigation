package com.worldview.myapplication.features.home

import androidx.navigation.compose.composable
import com.worldview.myapplication.navigation.NavGraphInstaller
import com.worldview.myapplication.navigation.NavigationCommand
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }

    single<NavGraphInstaller>(named("home"), createdAtStart = true) {
        {
            composable<NavigationCommand.Home> {
                HomeScreen()
            }
            composable<NavigationCommand.Settings> {
                SettingsScreen()
            }
        }
    }
}
