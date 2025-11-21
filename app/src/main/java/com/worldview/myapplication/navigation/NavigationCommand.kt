package com.worldview.myapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationCommand : NavKey {
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

val TOP_LEVEL_ROUTES: List<TopLevelDestination> =
    listOf(
        TopLevelDestination(
            NavigationCommand.Home,
            Icons.Default.Home,
        ),
        TopLevelDestination(
            NavigationCommand.ChatList,
            Icons.Default.Face,
        ),
        TopLevelDestination(
            NavigationCommand.Camera,
            Icons.Default.PlayArrow,
        ),
        TopLevelDestination(
            NavigationCommand.Settings,
            Icons.Default.Settings,
        ),
    )