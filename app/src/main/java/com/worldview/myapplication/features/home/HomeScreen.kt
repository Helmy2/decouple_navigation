package com.worldview.myapplication.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.onProfileButtonClicked() }) {
            Text("Go to Profile Page")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.onSettingsButtonClicked() }) {
            Text("Go to Settings Page")
        }
    }
}

