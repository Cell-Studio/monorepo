package com.cellstudio.vgc.ui.feature.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellstudio.vgc.domain.models.Pokemon
import com.cellstudio.vgc.domain.usecases.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class VgcViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(VgcState(listOf()))
    val state: StateFlow<VgcState> = _state.asStateFlow()

    private val _onNavigateToDetails = MutableSharedFlow<Int>()
    val onNavigateToDetails: SharedFlow<Int> = _onNavigateToDetails.asSharedFlow()

    private val _onNavigateToFilter = MutableSharedFlow<Unit>()
    val onNavigateToFilter: SharedFlow<Unit> = _onNavigateToFilter.asSharedFlow()

    fun onDataClicked(id: Int) {
        viewModelScope.launch {
            _onNavigateToDetails.emit(id)
        }
    }

    fun onEditClicked() {
        viewModelScope.launch {
            _onNavigateToFilter.emit(Unit)
        }
    }

    fun onFilterUpdated(name: String?) {
        viewModelScope.launch {
            val res = getPokemonListUseCase.execute(name = name)
            _state.update { it.copy(data = res) }
        }
    }
}

internal data class VgcState(
    val data: List<Pokemon>
)