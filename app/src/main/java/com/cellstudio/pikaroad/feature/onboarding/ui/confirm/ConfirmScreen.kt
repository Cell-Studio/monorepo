package com.cellstudio.pikaroad.feature.onboarding.ui.confirm

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

data class ConfirmScreenInput(
    val age: String
)

@Composable
fun ConfirmScreen(
    age: String,
    onSubmitClicked: () -> Unit,
    onEditClicked: () -> Unit
) {
    val vm = hiltViewModel<ConfirmViewModel, ConfirmViewModelFactory> { factory ->
        factory.create(ConfirmScreenInput(age))
    }

    LaunchedEffect(key1 = vm.onOutput) {
        Log.d("Testing", "123")
        vm.onOutput.collectLatest {
            Log.d("Testing", "onOutput")
            onSubmitClicked()
        }
    }

    val uiState by vm.state.collectAsStateWithLifecycle()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Row {
                Text(text = "Age")
                Text(text = uiState.age)
                TextButton(onClick = { onEditClicked() }) {
                    Text("Edit")
                }
            }
            Button(onClick = { vm.onSubmitClicked() }) {
                Text("Submit")
            }
        }
    }
}