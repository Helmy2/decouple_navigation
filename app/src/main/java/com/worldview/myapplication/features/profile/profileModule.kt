package com.worldview.myapplication.features.profile

import androidx.navigation3.runtime.entry
import com.worldview.myapplication.navigation.NavGraphInstaller
import com.worldview.myapplication.navigation.NavigationCommand
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val profileModule = module {
    viewModel { ProfileViewModel(get()) }
    single<NavGraphInstaller>(named("profile"), createdAtStart = true) {
        {
            entry<NavigationCommand.Profile> {
                ProfileScreen(userId = it.userId)
            }
        }
    }
}
