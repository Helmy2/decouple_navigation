package com.worldview.myapplication.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationCommand {
    @Serializable
    data object Home : NavigationCommand()

    @Serializable
    data object ChatList : NavigationCommand()

    @Serializable
    data object Camera : NavigationCommand()

    @Serializable
    data class Profile(val userId: String) : NavigationCommand()

    @Serializable
    data object Settings : NavigationCommand()
}

data class TopLevelDestination(
    val route: NavigationCommand,
    val selectedIcon: ImageVector,
)