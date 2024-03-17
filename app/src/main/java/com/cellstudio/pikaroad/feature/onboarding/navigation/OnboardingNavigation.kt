package com.cellstudio.pikaroad.feature.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cellstudio.pikaroad.feature.onboarding.ui.age.AgeScreen
import com.cellstudio.pikaroad.feature.onboarding.ui.confirm.ConfirmScreen
import com.cellstudio.pikaroad.feature.onboarding.ui.gender.GenderScreen

const val ONBOARDING_ROUTE = "onboarding"

const val AGE_SCREEN = "age"
const val GENDER_SCREEN = "gender"
const val CONFIRM_SCREEN = "confirm"

@Composable
fun OnboardingNavigationGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AGE_SCREEN,
    onSubmit: () -> Unit = {}
) {
    val vm = hiltViewModel<OnboardingViewModel>()
    val uiState by vm.state.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = AGE_SCREEN) {
            AgeScreen(
                age = uiState.age,
                onOutput = {
                    vm.onAction(VgcAction.UpdateAgeAction(it.age))
                    navController.navigate(GENDER_SCREEN)
                }
            )
        }

        composable(route = GENDER_SCREEN) {
            GenderScreen(
                gender = uiState.age,
                onOutput = {
                    vm.onAction(VgcAction.UpdateAgeAction(it.gender))
                    navController.navigate(GENDER_SCREEN)
                }
            )
        }

        composable(route = CONFIRM_SCREEN) {
            ConfirmScreen(
                age = uiState.age,
                onSubmitClicked = { onSubmit() },
                onEditClicked = {
                    navController.popBackStack(
                        route = AGE_SCREEN,
                        inclusive = false
                    )
                }
            )
        }
    }
}