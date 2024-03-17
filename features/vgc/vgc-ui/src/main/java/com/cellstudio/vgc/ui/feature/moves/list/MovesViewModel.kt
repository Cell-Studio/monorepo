package com.cellstudio.vgc.ui.feature.moves.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellstudio.vgc.domain.models.Move
import com.cellstudio.vgc.domain.usecases.GetMoveListUseCase
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

@HiltViewModel(assistedFactory = MovesViewModelFactory::class)
internal class MovesViewModel @AssistedInject constructor(
    @Assisted private val name: String?,
    private val getMoveListUseCase: GetMoveListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MovesState(listOf()))
    val state: StateFlow<MovesState> = _state.asStateFlow()

    private val _onNavigateToDetails = MutableSharedFlow<Int>()
    val onNavigateToDetails: SharedFlow<Int> = _onNavigateToDetails.asSharedFlow()

    private val _onNavigateToFilter = MutableSharedFlow<Unit>()
    val onNavigateToFilter: SharedFlow<Unit> = _onNavigateToFilter.asSharedFlow()

    init {
        viewModelScope.launch {
            val res = getMoveListUseCase.execute(name = name)
            _state.update { it.copy(data = res) }
        }
    }

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
}

internal data class MovesState(
    val data: List<Move>
)

@AssistedFactory
internal interface MovesViewModelFactory {
    fun create(name: String?): MovesViewModel
}

