package com.cellstudio.pikaroad.feature.onboarding.ui.confirm

import androidx.lifecycle.ViewModel
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

data class ConfirmScreenUIOutput(
    val age: String
)

@HiltViewModel(assistedFactory = ConfirmViewModelFactory::class)
class ConfirmViewModel @AssistedInject constructor(
    @Assisted val input: ConfirmScreenInput,
) : ViewModel() {

    private val _state = MutableStateFlow(ConfirmScreenUIOutput(input.age))
    val state: StateFlow<ConfirmScreenUIOutput> = _state.asStateFlow()

    private val _onOutput = MutableSharedFlow<Unit>()
    val onOutput: SharedFlow<Unit> = _onOutput.asSharedFlow()

    fun onSubmitClicked() {
        _onOutput.tryEmit(Unit)
    }
}

@AssistedFactory
interface ConfirmViewModelFactory {
    fun create(input: ConfirmScreenInput): ConfirmViewModel
}