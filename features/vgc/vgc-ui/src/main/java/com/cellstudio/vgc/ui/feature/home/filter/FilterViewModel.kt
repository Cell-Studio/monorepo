package com.cellstudio.vgc.ui.feature.home.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
internal class FilterViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(FilterState())
    val state: StateFlow<FilterState> = _state.asStateFlow()

    private val _onOutput = MutableSharedFlow<FilterOutput>()
    val onOutput: SharedFlow<FilterOutput> = _onOutput.asSharedFlow()

    fun onTextChanged(text: String) {
        _state.update { it.copy(name = text) }
    }

    fun onSubmitClicked() {
        viewModelScope.launch {
            _onOutput.emit(FilterOutput(name = state.value.name))
        }
    }
}

internal data class FilterState(
    val name: String = ""
) {
    val isSubmitEnabled = true
}

internal data class FilterOutput(
    val name: String = ""
)