package com.worldview.myapplication.features.home

import androidx.lifecycle.ViewModel
import com.worldview.myapplication.navigation.AppNavigator
import com.worldview.myapplication.navigation.NavigationCommand

class HomeViewModel(private val navigator: AppNavigator) : ViewModel() {
    fun onItemClicked(id: String) {
        navigator.navigate(NavigationCommand.Profile(id))
    }
}

