package com.worldview.myapplication.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState("")

    Scaffold {
        Column(
            modifier = Modifier.padding(it).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Home Screen")
            Text(state.toString())
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(10) { item ->
                    Card(
                        onClick = {
                            viewModel.onItemClicked("Item $item")
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Item $item", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}

