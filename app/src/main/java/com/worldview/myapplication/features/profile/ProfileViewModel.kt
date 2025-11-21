package com.worldview.myapplication.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.worldview.myapplication.navigation.AppNavigator
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val navigator: AppNavigator,
) : ViewModel() {
    fun onBackButtonClicked() {
        navigator.back()
    }

    fun setResult(result: String) {
        viewModelScope.launch {
            navigator.resultBus.setResult(result)
        }
    }
}

