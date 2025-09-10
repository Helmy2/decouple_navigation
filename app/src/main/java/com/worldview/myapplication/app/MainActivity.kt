package com.worldview.myapplication.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.worldview.myapplication.app.theme.MyApplicationTheme
import com.worldview.myapplication.navigation.AppNavigation
import com.worldview.myapplication.navigation.AppNavigator
import com.worldview.myapplication.navigation.NavGraphInstaller
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val navigator: AppNavigator by inject()
    private val navGraphInstallers: List<NavGraphInstaller> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface {
                    AppNavigation(
                        navController = navigator.backStack,
                        navGraphInstallers = navGraphInstallers
                    )
                }
            }
        }
    }
}
