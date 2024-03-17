package com.cellstudio.pikaroad.feature.onboarding.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class OnboardingState (
    val name: String = "",
    val ic: String = "",
    val gender: String = "",
    val age: String = "",
    val height: String = "",
    val weight: String = ""
)

internal sealed interface VgcAction {
    data class UpdateNameAction(private val name: String):
        com.cellstudio.pikaroad.feature.onboarding.navigation.VgcAction
    data class UpdateICAction(private val ic: String):
        com.cellstudio.pikaroad.feature.onboarding.navigation.VgcAction
    data class UpdateGenderAction(private val gender: String):
        com.cellstudio.pikaroad.feature.onboarding.navigation.VgcAction
    data class UpdateAgeAction(val age: String):
        com.cellstudio.pikaroad.feature.onboarding.navigation.VgcAction
    data class UpdateHeightAction(private val height: String):
        com.cellstudio.pikaroad.feature.onboarding.navigation.VgcAction
    data class UpdateWeightAction(private val weight: String):
        com.cellstudio.pikaroad.feature.onboarding.navigation.VgcAction
}

@HiltViewModel
internal class OnboardingViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state

    fun onAction(action: com.cellstudio.pikaroad.feature.onboarding.navigation.VgcAction) {
        when(action) {
            is com.cellstudio.pikaroad.feature.onboarding.navigation.VgcAction.UpdateAgeAction -> _state.update { it.copy(age = action.age) }
            else -> {}
        }
    }
}