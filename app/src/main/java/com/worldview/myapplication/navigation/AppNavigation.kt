package com.worldview.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Composable
fun AppNavigation(
    navController: SnapshotStateList<NavigationCommand>,
    navGraphInstallers: List<NavGraphInstaller>
) {
    NavDisplay(backStack = navController, entryProvider = entryProvider {
        navGraphInstallers.forEach { installer ->
            installer(this)
        }
    })
}
