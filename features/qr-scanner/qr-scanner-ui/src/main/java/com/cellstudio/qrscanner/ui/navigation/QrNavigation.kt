package com.cellstudio.qrscanner.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cellstudio.qrscanner.ui.ui.home.CameraScreen
import com.cellstudio.qrscanner.ui.ui.results.ResultScreen
import com.cellstudio.qrscanner.ui.ui.settings.SettingsScreen

const val QR_ROUTE = "qr-scanner"

private const val DATA = "data"

private const val HOME_SCREEN = "home"
private const val RESULT_SCREEN = "result?${DATA}={${DATA}}"
private const val SETTINGS_SCREEN = "settings"


fun NavGraphBuilder.qrNavGraph() {
    composable(route = QR_ROUTE) {
        HomeNavigation()
    }
}

@Composable
fun HomeNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = HOME_SCREEN) {
        composable(HOME_SCREEN) {
            CameraScreen(
                onResult = {
                    navController.navigate(
                        RESULT_SCREEN.replace(
                            oldValue = "{$DATA}",
                            newValue = it
                        )
                    )
                },
                onSettingsClicked = {
                    navController.navigate(SETTINGS_SCREEN)
                }
            )
        }

        composable(SETTINGS_SCREEN) {
            SettingsScreen(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = RESULT_SCREEN,
            arguments = listOf(
                navArgument(DATA) {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )
        ) {backStack ->
            val data = backStack.arguments?.getString(DATA) ?: ""
            ResultScreen(
                result = data,
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}