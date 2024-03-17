package com.cellstudio.vgc.ui.feature.home.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject



internal sealed interface VgcAction {
    data class UpdateFilterState(val name: String): VgcAction
}

@HiltViewModel
internal class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    fun onAction(action: VgcAction) {
        when(action) {
            is VgcAction.UpdateFilterState -> _state.update { it.copy(name = action.name) }
        }
    }
}

internal data class HomeState (
    val name: String = "",
)