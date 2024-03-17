package com.cellstudio.vgc.ui.feature.moves.list

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cellstudio.ui.table.DataColumn
import com.cellstudio.ui.table.DataTable
import com.cellstudio.vgc.ui.R
import com.cellstudio.vgc.ui.mapper.toMoveDamageTypeText
import com.cellstudio.vgc.ui.mapper.toMoveTypeText
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovesScreen(
    name: String? = null,
    onNavigateToDetails: (output: Int) -> Unit,
) {

    val vm = hiltViewModel<MovesViewModel, MovesViewModelFactory> { factory ->
        factory.create(name)
    }
    val uiState by vm.state.collectAsStateWithLifecycle()
    val layoutDirection = LocalLayoutDirection.current

    LaunchedEffect(key1 = vm.onNavigateToDetails) {
        vm.onNavigateToDetails.collectLatest {
            onNavigateToDetails(it)
        }
    }

    LaunchedEffect(key1 = vm.onNavigateToFilter) {
//        vm.onNavigateToFilter.collectLatest {
//            onNavigateToFilter()
//        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.moves_vgc_title)) },
                actions = {
                    IconButton(onClick = { vm.onEditClicked() }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                }
            )
        },
    ) { innerPadding ->
        DataTable(
            modifier = Modifier.padding(
                start = innerPadding.calculateStartPadding(layoutDirection) + 8.dp,
                end = innerPadding.calculateEndPadding(layoutDirection) + 8.dp,
                bottom = innerPadding.calculateBottomPadding(),
                top = innerPadding.calculateTopPadding(),
            ),
            columns = listOf(
                DataColumn(
                    title = stringResource(id = R.string.moves_vgc_name),
                    weight = 1.0f
                ) {
                    Text(text = it.name)
                },
                DataColumn(
                    title = stringResource(id = R.string.moves_vgc_power),
                    weight = 1.0f
                ) {
                    Text(text = "${it.power}")
                },
                DataColumn(
                    title = stringResource(id = R.string.moves_vgc_accuracy),
                    weight = 1.0f
                ) {
                    Text(text = "${it.accuracy}")
                },
                DataColumn(
                    title = stringResource(id = R.string.moves_vgc_type),
                    weight = 1.0f
                ) {
                    Text(text = stringResource(id = it.type.toMoveTypeText()))
                },
                DataColumn(
                    title = stringResource(id = R.string.moves_vgc_damage_type),
                    weight = 1.0f
                ) {
                    Text(text = stringResource(id = it.damageType.toMoveDamageTypeText()))
                },
            ),
            rows = uiState.data,
        ) {
            vm.onDataClicked(it.id)
        }
    }
}
