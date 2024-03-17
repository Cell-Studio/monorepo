package com.cellstudio.pikaroad.feature.onboarding.ui.gender

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

data class GenderScreenUIOutput(
    val gender: String
)

@HiltViewModel(assistedFactory = GenderViewModelFactory::class)
class GenderViewModel @AssistedInject constructor(
    @Assisted val input: GenderScreenInput,
) : ViewModel() {

    init {
        Log.d("Fuckerino", "Gender: ${input.gender}")
    }

    private val _state = MutableStateFlow(GenderScreenUIOutput(input.gender))
    val state: StateFlow<GenderScreenUIOutput> = _state.asStateFlow()

    private val _onOutput = MutableSharedFlow<GenderScreenOutput>()
    val onOutput: SharedFlow<GenderScreenOutput> = _onOutput.asSharedFlow()

    fun onTextChanged(text: String) {
        _state.update { it.copy(gender = text) }
    }

    fun onSubmitClicked() {
        Log.d("Testing", "GG")
        viewModelScope.launch {
            _onOutput.emit(GenderScreenOutput(gender = state.value.gender))
        }
    }
}

@AssistedFactory
interface GenderViewModelFactory {
    fun create(input: GenderScreenInput): GenderViewModel
}