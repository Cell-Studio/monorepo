package com.cellstudio.pikaroad.feature.onboarding.ui.age

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AgeScreenUIOutput(
    val age: String
)

class AgeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(AgeScreenUIOutput(age = ""))
    val state: StateFlow<AgeScreenUIOutput> = _state.asStateFlow()

    private val _onOutput = MutableSharedFlow<AgeScreenOutput>()
    val onOutput: SharedFlow<AgeScreenOutput> = _onOutput.asSharedFlow()

    fun onTextChanged(text: String) {
        _state.update { it.copy(age = text) }
    }

    fun onSubmitClicked() {
        viewModelScope.launch {
            _onOutput.emit(AgeScreenOutput(age = state.value.age))
        }
    }
}