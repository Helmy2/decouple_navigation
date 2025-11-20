package com.worldview.myapplication.features.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.worldview.myapplication.navigation.AppNavigator

class ProfileViewModel(
    private val navigator: AppNavigator,
    userId: String
) : ViewModel() {

    val currentUserId: MutableState<String?> = mutableStateOf(null)

    init {
        currentUserId.value = userId
    }

    fun onBackButtonClicked() {
        navigator.back()
    }
}

