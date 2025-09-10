package com.worldview.myapplication.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf


class AppNavigator {
    private val _commands = mutableStateOf<Any?>(null)
    val commands: State<Any?> = _commands
    fun navigate(command: Any) {
        _commands.value = command
    }

    fun onCommandConsumed() {
        _commands.value = null
    }

    fun back(){
        _commands.value = GoBack
    }

    companion object {
        data object GoBack
    }
}

