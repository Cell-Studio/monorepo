package com.cellstudio.tcg.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.cellstudio.tcg.ui.ui.home.TcgScreen
import com.cellstudio.vgc.ui.feature.details.TcgDetailsScreen

const val TCG_ROUTE = "tcg"

private const val DETAILS_SCREEN_ID = "id"

private const val HOME_SCREEN = "home"
private const val DETAILS_SCREEN = "tcg/details/{$DETAILS_SCREEN_ID}"


fun NavGraphBuilder.tcgNavGraph(
    navController: NavHostController,
) {
    navigation(startDestination = HOME_SCREEN, route = TCG_ROUTE) {
            composable(HOME_SCREEN) {
                TcgScreen {
                    navController.navigate(
                        DETAILS_SCREEN.replace(
                        oldValue = "{$DETAILS_SCREEN_ID}",
                        newValue = it
                        )
                    )
                }
            }
            composable(
                route = DETAILS_SCREEN,
                arguments = listOf(
                    navArgument(DETAILS_SCREEN_ID) { type = NavType.StringType }
                )
            ) {
                val id = it.arguments?.getString(DETAILS_SCREEN_ID)
                id?.let {
                    TcgDetailsScreen(id = it)
                }
            }
    }
}