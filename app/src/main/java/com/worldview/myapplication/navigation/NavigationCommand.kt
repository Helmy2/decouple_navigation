package com.worldview.myapplication.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationCommand {
    @Serializable
    data object Home : NavigationCommand()
    @Serializable
    data class Profile(val userId: String) : NavigationCommand()
    @Serializable
    data object Settings : NavigationCommand()
}