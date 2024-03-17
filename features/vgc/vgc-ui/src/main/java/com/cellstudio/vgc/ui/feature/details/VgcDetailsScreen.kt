package com.cellstudio.vgc.ui.feature.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cellstudio.vgc.domain.models.Ability
import com.cellstudio.vgc.domain.models.Move
import com.cellstudio.vgc.domain.models.PokemonType
import com.cellstudio.vgc.domain.models.Stats
import com.cellstudio.vgc.ui.R

@Composable
internal fun VgcDetailsScreen(
    id: Int,
    onOpenTcg: (String) -> Unit
) {
    val vm = hiltViewModel<VgcDetailsViewModel, VgcDetailsViewModelFactory> { factory ->
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
                DetailsImage(
                    image = uiState.details.image.official,
                )
            }

            item {
                DetailsHeader(
                    name = uiState.details.name,
                    types = uiState.details.type,
                    onTypeClicked = {},
                    onOpenTcg = onOpenTcg
                )
            }

            item {
                DetailsStatsSection(
                    stats = uiState.details.stats,
                )
            }

            item {
                DetailsAbilitySection(
                    abilities = uiState.details.abilities,
                )
            }

            item {
                DetailsMoveSection(
                    moves = uiState.details.moves,
                )
            }
        }
    }
}

@Composable
internal fun DetailsImage(
    image: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(256.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = null,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailsHeader(
    name: String,
    types: List<PokemonType>,
    onTypeClicked: (PokemonType) -> Unit,
    onOpenTcg: (String) -> Unit
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
            TextButton(onClick = { onOpenTcg(name) }) {
                Text(text = stringResource(id = R.string.details_vgc_open_tcg, name))
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = stringResource(id = R.string.details_vgc_types))
            types.forEach {
                SuggestionChip(
                    onClick = { onTypeClicked(it) },
                    label = { Text(it.name) }
                )
            }
        }
    }
}

@Composable
internal fun DetailsAbilitySection(
    abilities: List<Ability>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(text = stringResource(id = R.string.details_vgc_abilities))
        abilities.forEach {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Column(modifier = Modifier.weight(1.0f)){
                    Text(
                        text = it.name,
                    )
                    if (it.isHidden) {
                        Text(
                            text = stringResource(R.string.details_vgc_hidden),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
                Text(
                    text = it.description,
                    modifier = Modifier.weight(6.0f)
                )
            }
        }
    }
}

@Composable
internal fun DetailsMoveSection(
    moves: List<Move>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(text = stringResource(id = R.string.details_vgc_moves))
        moves.forEach {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = it.name,
                    modifier = Modifier.weight(1.0f)
                )
                Text(
                    text = "${it.accuracy}",
                    modifier = Modifier.weight(1.0f)
                )
                Text(
                    text = "${it.power}",
                    modifier = Modifier.weight(1.0f)
                )
            }
        }
    }
}

@Composable
internal fun DetailsStatsSection(
    stats: Map<Stats, Int>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = stringResource(id = R.string.details_vgc_stats),
        )
        stats.forEach {
            Text(
                text = "${it.key.name}: ${it.value}",
            )
        }
    }
}