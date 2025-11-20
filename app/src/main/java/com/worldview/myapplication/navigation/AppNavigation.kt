package com.worldview.myapplication.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.ui.NavDisplay
import com.worldview.myapplication.navigation.AppNavigator.Companion.TOP_LEVEL_ROUTES
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AppNavigation(
    navController: AppNavigator,
) {
    val entryProvider = koinEntryProvider()

    Scaffold(
        contentWindowInsets = WindowInsets(),
        bottomBar = {
            AnimatedVisibility(
                navController.shouldShowAppBar()
            ) {
                NavigationBar {
                    TOP_LEVEL_ROUTES.forEach { topLevelRoute ->
                        val isSelected =
                            navController.isSelected(topLevelRoute.route)
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                navController.addTopLevel(topLevelRoute.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = topLevelRoute.selectedIcon,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        }
    ) {
        NavDisplay(
            backStack = navController.backStack,
            entryProvider = entryProvider,
            modifier = Modifier.padding(it)
        )
    }
}
