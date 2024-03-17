package com.cellstudio.vgc.ui.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cellstudio.vgc.ui.feature.home.filter.FilterScreen
import com.cellstudio.vgc.ui.feature.home.home.VgcScreen

private const val HOME_SCREEN = "home"
private const val FILTER_SCREEN = "filter"

@Composable
fun HomeNavigation(
    navController: NavHostController = rememberNavController(),
    onOutput: (output: Int) -> Unit
) {
    val vm = hiltViewModel<HomeViewModel>()
    val uiState by vm.state.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = HOME_SCREEN) {
        composable(route = HOME_SCREEN) {
            VgcScreen(
                name = uiState.name,
                onNavigateToDetails = onOutput,
                onNavigateToFilter = {
                    navController.navigate(FILTER_SCREEN)
                }
            )
        }

        composable(route = FILTER_SCREEN) {
            FilterScreen(
                name = uiState.name,
                onBackPressed = { navController.popBackStack() },
                onOutput = {
                    vm.onAction(VgcAction.UpdateFilterState(it.name))
                    navController.popBackStack(
                        route = HOME_SCREEN,
                        inclusive = false
                    )
                }
            )
        }
    }
}