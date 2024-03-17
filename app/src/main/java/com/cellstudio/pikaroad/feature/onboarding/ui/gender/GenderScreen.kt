package com.cellstudio.pikaroad.feature.onboarding.ui.gender

import android.util.Log
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

data class GenderScreenInput(
    val gender: String
)

data class GenderScreenOutput(
    val gender: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderScreen(
    gender: String,
    onOutput: (output: GenderScreenOutput) -> Unit
) {
    val viewModel = hiltViewModel<GenderViewModel, GenderViewModelFactory> { factory ->
        factory.create(GenderScreenInput(gender))
    }

    LaunchedEffect(key1 = viewModel.onOutput) {
        Log.d("Testing", "123")
        viewModel.onOutput.collectLatest {
            Log.d("Testing", "onOutput")
            onOutput(it)
        }
    }

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Text(text = "Gender")
            TextField(
                onValueChange = { viewModel.onTextChanged(it)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(32.dp),
                value = uiState.gender,
                maxLines = 1,
                singleLine = true,
            )
            Button(onClick = { viewModel.onSubmitClicked() }) {
                Text("Submit")
            }
        }
    }
}