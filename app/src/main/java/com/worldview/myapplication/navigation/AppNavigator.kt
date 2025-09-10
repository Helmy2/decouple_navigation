package com.worldview.myapplication.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList


class AppNavigator(startDestination: NavigationCommand) {
    val backStack: SnapshotStateList<NavigationCommand> = mutableStateListOf(startDestination)

    fun navigate(command: NavigationCommand) {
        backStack.add(command)
    }

    fun back(){
        backStack.removeLastOrNull()
    }
}

