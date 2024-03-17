package com.cellstudio.vgc.ui.feature.home.filter

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cellstudio.vgc.ui.R
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FilterScreen(
    name: String? = null,
    onBackPressed: () -> Unit,
    onOutput: (output: FilterOutput) -> Unit
) {
    val viewModel = hiltViewModel<FilterViewModel>()

    LaunchedEffect(key1 = viewModel.onOutput) {
        viewModel.onOutput.collectLatest {
            onOutput(it)
        }
    }

    LaunchedEffect(key1 = name) {
        name?.let {
            viewModel.onTextChanged(it)
        }
    }

    BackHandler { onBackPressed() }

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                enabled = uiState.isSubmitEnabled,
                onClick = { viewModel.onSubmitClicked() },
                contentPadding = PaddingValues(all = 16.dp)
            ) {
                Text(stringResource(id = R.string.filter_vgc_submit))
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            OutlinedTextField(
                label = { Text(text = stringResource(id = R.string.filter_vgc_name)) },
                onValueChange = { viewModel.onTextChanged(it)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(4.dp),
                value = uiState.name,
                maxLines = 1,
                singleLine = true,
            )

        }
    }
}