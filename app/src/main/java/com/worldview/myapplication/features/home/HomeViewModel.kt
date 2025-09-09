package com.worldview.myapplication.features.home

import androidx.lifecycle.ViewModel
import com.worldview.myapplication.navigation.AppNavigator
import com.worldview.myapplication.navigation.NavigationCommand

class HomeViewModel(private val navigator: AppNavigator) : ViewModel() {
    fun onProfileButtonClicked() {
        navigator.navigate(NavigationCommand.Profile("user-from-home-999"))
    }
    fun onSettingsButtonClicked() {
        navigator.navigate(NavigationCommand.Settings)
    }
}

