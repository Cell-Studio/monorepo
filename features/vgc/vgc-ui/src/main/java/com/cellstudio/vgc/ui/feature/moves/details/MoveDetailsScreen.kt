package com.cellstudio.vgc.ui.feature.moves.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cellstudio.vgc.domain.models.MoveType
import com.cellstudio.vgc.ui.R
import com.cellstudio.vgc.ui.mapper.toMoveTypeText

@Composable
internal fun MoveDetailsScreen(
    id: Int,
) {
    val vm = hiltViewModel<MoveDetailsViewModel, MoveDetailsViewModelFactory> { factory ->
        factory.create(id)
    }

    val uiState by vm.state.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                DetailsHeader(
                    name = uiState.details.name,
                    type = uiState.details.type,
                    onTypeClicked = {},
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailsHeader(
    name: String,
    type: MoveType,
    onTypeClicked: (MoveType) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(id = R.string.details_vgc_name, name))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = stringResource(id = R.string.details_vgc_types))
            SuggestionChip(
                onClick = { onTypeClicked(type) },
                label = { Text(stringResource(id = type.toMoveTypeText())) }
            )
        }
    }
}
