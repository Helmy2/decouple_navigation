package com.worldview.myapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.mutableStateListOf

class AppNavigator(startDestination: NavigationCommand) {
    companion object {
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
    }

    // Expose the back stack so it can be rendered by the NavDisplay
    val backStack = mutableStateListOf(startDestination)

    fun navigate(command: NavigationCommand) {
        // Prevent adding duplicate destinations to the back stack
        if (backStack.lastOrNull() != command) {
            backStack.add(command)
        }
    }

    fun back() {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    fun addTopLevel(command: NavigationCommand) {
        if (backStack.firstOrNull() == command) {
            // If the current top-level destination is re-selected, pop back to it
            if (backStack.size > 1) {
                backStack.removeRange(1, backStack.size)
            }
        } else {
            // When switching to a new top-level destination, clear the back stack and add the new one
            backStack.clear()
            backStack.add(command)
        }
    }

    fun isSelected(command: NavigationCommand): Boolean {
        return backStack.firstOrNull() == command
    }

    fun shouldShowAppBar(): Boolean {
        return TOP_LEVEL_ROUTES.any { it.route == backStack.lastOrNull() }
    }
}
