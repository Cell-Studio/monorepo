package com.cellstudio.vgc.ui.feature.moves.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellstudio.vgc.domain.models.MoveDamageType
import com.cellstudio.vgc.domain.models.MoveDetails
import com.cellstudio.vgc.domain.models.MoveType
import com.cellstudio.vgc.domain.usecases.GetMoveDetailsUseCase
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

@HiltViewModel(assistedFactory = MoveDetailsViewModelFactory::class)
internal class MoveDetailsViewModel @AssistedInject constructor(
    @Assisted val id: Int,
    private val getMoveDetailsUseCase: GetMoveDetailsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MoveDetailsState())
    val state: StateFlow<MoveDetailsState> = _state.asStateFlow()

    private val _onOutput = MutableSharedFlow<String>()
    val onOutput: SharedFlow<String> = _onOutput.asSharedFlow()

    init {
        viewModelScope.launch {
            val res = getMoveDetailsUseCase.execute(id = id)
            _state.update { it.copy(details = res) }
        }
    }
}

@AssistedFactory
internal interface MoveDetailsViewModelFactory {
    fun create(id: Int): MoveDetailsViewModel
}

internal data class MoveDetailsState(
    val details: MoveDetails = MoveDetails(-1, "", -1, -1, MoveType.UNKNOWN, MoveDamageType.UNKNOWN)
)