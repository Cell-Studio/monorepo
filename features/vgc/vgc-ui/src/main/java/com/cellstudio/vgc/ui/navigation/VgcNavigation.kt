package com.cellstudio.vgc.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.cellstudio.vgc.ui.feature.dashboard.DashboardScreen
import com.cellstudio.vgc.ui.feature.details.VgcDetailsScreen
import com.cellstudio.vgc.ui.feature.moves.details.MoveDetailsScreen

const val VGC_ROUTE = "vgc"

internal const val DETAILS_SCREEN_ID = "id"

internal const val HOME_SCREEN = "home"
internal const val MOVE_SCREEN = "move"
internal const val DETAILS_SCREEN = "vgc/details/{$DETAILS_SCREEN_ID}"
internal const val MOVE_DETAILS_SCREEN = "move/details/{$DETAILS_SCREEN_ID}"


fun NavGraphBuilder.vgcNavGraph(
    navController: NavHostController,
    onOpenTcg: (String) -> Unit
) {
    navigation(startDestination = HOME_SCREEN, route = VGC_ROUTE) {
        composable(HOME_SCREEN) {
            DashboardScreen(
                onNavigateToVgcDetails = {
                    navController.navigate(
                        DETAILS_SCREEN.replace(
                            oldValue = "{$DETAILS_SCREEN_ID}",
                            newValue = "$it"
                        )
                    )
                },
                onNavigateToMoveDetails = {
                    navController.navigate(
                        MOVE_DETAILS_SCREEN.replace(
                            oldValue = "{$DETAILS_SCREEN_ID}",
                            newValue = "$it"
                        )
                    )
                }
            )
        }
        composable(
            route = DETAILS_SCREEN,
            arguments = listOf(
                navArgument(DETAILS_SCREEN_ID) { type = NavType.IntType }
            )
        ) {
            val id = it.arguments?.getInt(DETAILS_SCREEN_ID)
            id?.let {
                VgcDetailsScreen(id = it) {
                    onOpenTcg(it)
                }
            }
        }
        composable(
            route = MOVE_DETAILS_SCREEN,
            arguments = listOf(
                navArgument(DETAILS_SCREEN_ID) { type = NavType.IntType }
            )
        ) {
            val id = it.arguments?.getInt(DETAILS_SCREEN_ID)
            id?.let {
                MoveDetailsScreen(id = it)
            }
        }
    }
}