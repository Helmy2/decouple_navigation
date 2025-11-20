package com.worldview.myapplication.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.worldview.myapplication.features.home.HomeScreen
import com.worldview.myapplication.features.home.SettingsScreen
import com.worldview.myapplication.features.profile.ProfileScreen
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AppNavigation(
    navController: AppNavigator,
) {
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()

    val navigationSuiteType = remember(windowAdaptiveInfo, navController.shouldShowAppBar()) {
        val calculateFromAdaptiveInfo =
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(windowAdaptiveInfo)
        if (calculateFromAdaptiveInfo == NavigationSuiteType.NavigationBar) {
            if (navController.shouldShowAppBar()) {
                NavigationSuiteType.NavigationBar
            } else {
                NavigationSuiteType.None
            }
        } else calculateFromAdaptiveInfo
    }
    val directive = remember(windowAdaptiveInfo) {
        calculatePaneScaffoldDirective(windowAdaptiveInfo)
    }
    val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>(directive = directive)


    NavigationSuiteScaffold(
        layoutType = navigationSuiteType,
        navigationSuiteItems = {
            AppNavigator.TOP_LEVEL_ROUTES.forEach { topLevelRoute ->
                val isSelected =
                    navController.isSelected(topLevelRoute.route)
                item(
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
    ) {
        NavDisplay(
            sceneStrategy = listDetailStrategy,
            backStack = navController.backStack,
            entryProvider = entryProvider {
                entry<NavigationCommand.Home>(
                    metadata = ListDetailSceneStrategy.listPane()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Cyan.copy(.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        HomeScreen()
                    }
                }
                entry<NavigationCommand.Settings> {
                    SettingsScreen()
                }
                entry<NavigationCommand.Camera> {

                }
                entry<NavigationCommand.ChatList> {

                }
                entry<NavigationCommand.Profile>(
                    metadata = ListDetailSceneStrategy.detailPane()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Magenta.copy(.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        ProfileScreen(id = it.userId)
                    }
                }
            },
        )
    }
}
