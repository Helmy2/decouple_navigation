package com.worldview.myapplication.features.home

import com.worldview.myapplication.navigation.NavigationCommand
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val homeModule = module {
    viewModel { HomeViewModel(get()) }

    navigation<NavigationCommand.Home> {
        HomeScreen()
    }
    navigation<NavigationCommand.Settings> {
        SettingsScreen()
    }
    navigation<NavigationCommand.Camera> {

    }
    navigation<NavigationCommand.ChatList> {

    }
}
