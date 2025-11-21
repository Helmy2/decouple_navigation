package com.worldview.myapplication.features.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    id: String,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Profile Screen for User: $id")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.onBackButtonClicked() }) {
            Text("Go Back")
        }
        Button(onClick = { viewModel.setResult(id) }) {
            Text("Set Result")
        }
    }
}


