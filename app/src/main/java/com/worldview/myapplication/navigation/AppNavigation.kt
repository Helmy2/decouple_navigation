package com.worldview.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(
    navController: NavHostController,
    navigator: AppNavigator,
    navGraphInstallers: List<NavGraphInstaller>
) {
    LaunchedEffect(navigator.commands.value) {
        navigator.commands.value?.let { command ->
            when (command) {
                is AppNavigator.Companion.GoBack -> navController.popBackStack()
                is NavigationCommand -> navController.navigate(command)
            }
            navigator.onCommandConsumed()
        }
    }
    NavHost(navController = navController, startDestination = NavigationCommand.Home) {
        navGraphInstallers.forEach { installer ->
            installer(this)
        }
    }
}
