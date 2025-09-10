package com.worldview.myapplication.navigation

import androidx.navigation3.runtime.EntryProviderBuilder

typealias NavGraphInstaller = EntryProviderBuilder<NavigationCommand>.() -> Unit
