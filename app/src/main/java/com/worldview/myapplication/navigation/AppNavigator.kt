package com.worldview.myapplication.navigation

import android.os.Bundle
import androidx.compose.runtime.mutableStateListOf
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.serialization.json.Json

/**
 * Manages the app's back stack and top-level navigation state using an observable list.
 *
 * @property resultBus Shared event bus for decoupled screen-to-screen result passing.
 */
class AppNavigator(
    startDestination: NavigationCommand,
    val resultBus: ResultBus = ResultBus()
) : SavedStateRegistry.SavedStateProvider {
    /**
     * Observable history of visited destinations. The last item is the active screen.
     * Exposed as [mutableStateListOf] for Compose reactivity.
     */
    val backStack = mutableStateListOf(startDestination)

    private val KEY_BACK_STACK = "key_nav_back_stack"

    /**
     * Connects this Navigator to the Activity's SavedStateRegistry.
     * MUST be called in MainActivity.onCreate().
     */
    fun attachToRegistry(owner: SavedStateRegistryOwner) {
        val registry = owner.savedStateRegistry
        registry.registerSavedStateProvider(KEY_BACK_STACK, this)

        val savedBundle = registry.consumeRestoredStateForKey(KEY_BACK_STACK)
        if (savedBundle != null) {
            val jsonList = savedBundle.getStringArrayList(KEY_BACK_STACK)
            if (!jsonList.isNullOrEmpty()) {
                backStack.clear()
                val restoredStack = jsonList.map { Json.decodeFromString<NavigationCommand>(it) }
                backStack.addAll(restoredStack)
            }
        }
    }

    /**
     * Called by Android when the process is being killed or configuration changes.
     * We serialize the current backStack to a list of JSON strings.
     */
    override fun saveState(): Bundle {
        return Bundle().apply {
            val jsonList = ArrayList(backStack.map { Json.encodeToString(it) })
            putStringArrayList(KEY_BACK_STACK, jsonList)
        }
    }

    /**
     * Pushes [command] to [backStack].
     *
     * @param command The destination to navigate to.
     * @param singleTop If true (default), replaces the current screen if it's the same class
     *                  to prevent duplicate stacking (e.g., double-tap protection).
     */
    fun navigate(command: NavigationCommand, singleTop: Boolean = true) {
        val current = backStack.lastOrNull()

        if (singleTop && current != null && current::class == command::class) {
            backStack[backStack.lastIndex] = command
        } else {
            backStack.add(command)
        }
    }

    /**
     * Clears the entire back stack and sets [command] as the new root.
     *
     * **Use Case:** Logging out, completing a checkout flow, or resetting the app state.
     */
    fun navigateAsStart(command: NavigationCommand) {
        backStack.clear()
        backStack.add(command)
    }

    /** Safe pop of the last destination. Does nothing if only one screen remains. */
    fun back() {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    /**
     * Switches to a top-level root (Bottom Nav Tab).
     * - If re-selecting the current tab: Pops to root.
     * - If switching tabs: Resets stack to the new root.
     */
    fun navigateToTopLevel(command: NavigationCommand) {
        if (backStack.firstOrNull() == command) {
            // Re-selected current tab: Pop everything above the root
            if (backStack.size > 1) {
                backStack.removeRange(1, backStack.size)
            }
        } else {
            // Switch tab: Reset stack completely
            navigateAsStart(command)
        }
    }

    /** Returns true if [command] is the current bottom-most root destination. */
    fun isAtBottomOfStack(command: NavigationCommand): Boolean {
        return backStack.firstOrNull() == command
    }

    /** Returns true if the App Bar should be shown (when not on a top-level route). */
    fun shouldShowAppBar(): Boolean {
        return TOP_LEVEL_ROUTES.any { it.route == backStack.lastOrNull() }
    }
}
