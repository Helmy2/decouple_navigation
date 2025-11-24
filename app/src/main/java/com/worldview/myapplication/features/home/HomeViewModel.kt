package com.worldview.myapplication.features.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.worldview.myapplication.navigation.AppNavigator
import com.worldview.myapplication.navigation.NavigationCommand
import kotlinx.coroutines.launch

class HomeViewModel(
    private val navigator: AppNavigator
) : ViewModel() {
    val state = mutableStateOf("")

    init {
        viewModelScope.launch {
            navigator.resultBus.getResult<String>().collect {
                state.value = it ?: ""
            }
        }
    }

    fun onItemClicked(id: String) {
        navigator.navigate(NavigationCommand.Profile(id), singleTop = true)
    }
}

