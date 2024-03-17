package com.cellstudio.pikaroad.feature.onboarding.ui.age

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

data class AgeScreenOutput(
    val age: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeScreen(
    age: String,
    onOutput: (output: AgeScreenOutput) -> Unit
) {
    val viewModel = hiltViewModel<AgeViewModel>()

    LaunchedEffect(key1 = viewModel.onOutput) {
        viewModel.onOutput.collectLatest {
            onOutput(it)
        }
    }

    LaunchedEffect(key1 = age) {
        viewModel.onTextChanged(age)
    }

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Text(text = "Age")
            TextField(
                onValueChange = { viewModel.onTextChanged(it)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(32.dp),
                value = uiState.age,
                maxLines = 1,
                singleLine = true,
            )
            Button(onClick = { viewModel.onSubmitClicked() }) {
                Text("Submit")
            }
        }
    }
}