package com.worldview.myapplication.features.profile

import com.worldview.myapplication.navigation.NavigationCommand
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val profileModule = module {
    viewModelOf(::ProfileViewModel)

    navigation<NavigationCommand.Profile> {
        ProfileScreen(
            viewModel = koinViewModel(
                parameters = { parametersOf(it.userId) }
            )
        )
    }
}
