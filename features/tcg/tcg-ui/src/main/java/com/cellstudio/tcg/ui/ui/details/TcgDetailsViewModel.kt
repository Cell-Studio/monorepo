package com.cellstudio.vgc.ui.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellstudio.tcg.domain.models.PokemonCardDetails
import com.cellstudio.tcg.domain.usecases.GetPokemonCardDetailsUseCase
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

@HiltViewModel(assistedFactory = TcgDetailsViewModelFactory::class)
internal class TcgDetailsViewModel @AssistedInject constructor(
    @Assisted val id: String,
    private val getPokemonCardDetailsUseCase: GetPokemonCardDetailsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(TcgDetailsState())
    val state: StateFlow<TcgDetailsState> = _state.asStateFlow()

    private val _onOutput = MutableSharedFlow<String>()
    val onOutput: SharedFlow<String> = _onOutput.asSharedFlow()

    init {
        viewModelScope.launch {
            val res = getPokemonCardDetailsUseCase.execute(id = id)
            _state.update { it.copy(details = res) }
        }
    }
}

@AssistedFactory
internal interface TcgDetailsViewModelFactory {
    fun create(id: String): TcgDetailsViewModel
}

internal data class TcgDetailsState(
    val details: PokemonCardDetails = PokemonCardDetails("", "", "")
)