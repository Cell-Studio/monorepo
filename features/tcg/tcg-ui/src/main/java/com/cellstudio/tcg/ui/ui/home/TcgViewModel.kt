package com.cellstudio.tcg.ui.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellstudio.tcg.domain.models.PokemonCard
import com.cellstudio.tcg.domain.usecases.GetPokemonCardListUseCase
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
internal class TcgViewModel @Inject constructor(
    private val getPokemonCardListUseCase: GetPokemonCardListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(VgcState(listOf()))
    val state: StateFlow<VgcState> = _state.asStateFlow()

    private val _onOutput = MutableSharedFlow<String>()
    val onOutput: SharedFlow<String> = _onOutput.asSharedFlow()

    init {
        viewModelScope.launch {
            val res = getPokemonCardListUseCase.execute("charizard")
            _state.update { it.copy(data = res) }
        }
    }

    fun onDataClicked(id: String) {
        viewModelScope.launch {
            _onOutput.emit(id)
        }
    }
}

internal data class VgcState(
    val data: List<PokemonCard>
)