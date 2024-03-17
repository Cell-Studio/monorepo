package com.cellstudio.vgc.ui.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellstudio.vgc.domain.models.PokemonDetails
import com.cellstudio.vgc.domain.models.PokemonImage
import com.cellstudio.vgc.domain.usecases.GetPokemonDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = VgcDetailsViewModelFactory::class)
internal class VgcDetailsViewModel @AssistedInject constructor(
    @Assisted val id: Int,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(VgcDetailsState())
    val state: StateFlow<VgcDetailsState> = _state.asStateFlow()

    private val _onOutput = MutableSharedFlow<String>()
    val onOutput: SharedFlow<String> = _onOutput.asSharedFlow()

    init {
        viewModelScope.launch {
            val res = getPokemonDetailsUseCase.execute(id = id)
            _state.update { it.copy(details = res) }
        }
    }
}

@AssistedFactory
internal interface VgcDetailsViewModelFactory {
    fun create(id: Int): VgcDetailsViewModel
}

internal data class VgcDetailsState(
    val details: PokemonDetails = PokemonDetails(-1, "", PokemonImage("", "", ""), mapOf(), listOf(), listOf(), listOf())
)