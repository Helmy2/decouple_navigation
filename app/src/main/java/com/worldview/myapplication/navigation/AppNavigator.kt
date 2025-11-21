package com.worldview.myapplication.navigation

import androidx.compose.runtime.mutableStateListOf

/**
 * Manages the app's back stack and top-level navigation state using an observable list.
 *
 * @property resultBus Shared event bus for decoupled screen-to-screen result passing.
 */
class AppNavigator(
    startDestination: NavigationCommand,
    val resultBus: ResultBus = ResultBus()
) {
    /**
     * Observable history of visited destinations. The last item is the active screen.
     * Exposed as [mutableStateListOf] for Compose reactivity.
     */
    val backStack = mutableStateListOf(startDestination)

    /**
     * Pushes [command] to [backStack]. Replaces the current screen if it's the same type
     * to prevent duplicate stacking.
     */
    fun navigate(command: NavigationCommand) {
        val current = backStack.lastOrNull()
        if (current != null && current::class == command::class) {
            back()
        }
        backStack.add(command)
    }

    /** Safe pop of the last destination. Does nothing if only one screen remains. */
    fun back() {
        backStack.removeLastOrNull()
    }

    /**
     * Switches to a top-level root. Clears stack on switch; pops to root if re-selected.
     */
    fun addTopLevel(command: NavigationCommand) {
        if (backStack.firstOrNull() == command) {
            if (backStack.size > 1) backStack.removeRange(1, backStack.size)
        } else {
            backStack.clear()
            backStack.add(command)
        }
    }

    /** Returns true if [command] is the current bottom-most root destination. */
    fun isSelected(command: NavigationCommand): Boolean {
        return backStack.firstOrNull() == command
    }

    /** Returns true if the App Bar should be shown (when not on a top-level route). */
    fun shouldShowAppBar(): Boolean {
        return TOP_LEVEL_ROUTES.any { it.route == backStack.lastOrNull() }
    }
}
