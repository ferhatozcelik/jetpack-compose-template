package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ferhatozcelik.jetpackcomposetemplate.navigation.Screen

@Composable
fun MainScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.LoadExamples)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val state = uiState) {
            HomeUiState.Idle -> Text(text = "Idle")
            HomeUiState.Loading -> CircularProgressIndicator()
            is HomeUiState.Success -> Text(text = "Loaded ${state.items.size} example item(s)")
            is HomeUiState.Error -> Text(text = state.message)
        }

        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = { navController.navigate(Screen.Detail(id = 123)) }
        ) {
            Text(text = "Go to Detail")
        }
    }
}
