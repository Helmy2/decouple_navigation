package com.worldview.myapplication.features.profile

import androidx.lifecycle.ViewModel
import com.worldview.myapplication.navigation.AppNavigator

class ProfileViewModel(private val navigator: AppNavigator) : ViewModel() {
    fun onBackButtonClicked() {
        navigator.back()
    }
}

