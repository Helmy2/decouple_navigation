package com.worldview.myapplication.features.profile

import android.util.Log
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.worldview.myapplication.navigation.NavGraphInstaller
import com.worldview.myapplication.navigation.NavigationCommand
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val profileModule = module {
    viewModel { ProfileViewModel(get()) }
    single<NavGraphInstaller>(named("profile"), createdAtStart = true) {
        {
            composable<NavigationCommand.Profile> { backStackEntry ->
                val profileArgs: NavigationCommand.Profile = backStackEntry.toRoute()
                ProfileScreen(userId = profileArgs.userId)
            }
        }
    }
}
