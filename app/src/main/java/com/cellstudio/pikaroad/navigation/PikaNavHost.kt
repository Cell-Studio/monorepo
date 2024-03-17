package com.cellstudio.pikaroad.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cellstudio.pikaroad.feature.home.ui.home.HOME_ROUTE
import com.cellstudio.pikaroad.feature.home.ui.home.HomeScreen
import com.cellstudio.pikaroad.feature.onboarding.navigation.AGE_SCREEN
import com.cellstudio.pikaroad.feature.onboarding.navigation.ONBOARDING_ROUTE
import com.cellstudio.pikaroad.feature.onboarding.navigation.OnboardingNavigationGraph
import com.cellstudio.qrscanner.ui.navigation.QR_ROUTE
import com.cellstudio.qrscanner.ui.navigation.qrNavGraph
import com.cellstudio.tcg.ui.navigation.TCG_ROUTE
import com.cellstudio.tcg.ui.navigation.tcgNavGraph
import com.cellstudio.vgc.ui.navigation.VGC_ROUTE
import com.cellstudio.vgc.ui.navigation.vgcNavGraph

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun PikaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(HOME_ROUTE) {
            HomeScreen(
                onOnboardingClicked = { navController.navigate(ONBOARDING_ROUTE) },
                onVgcClicked = { navController.navigate(VGC_ROUTE) },
                onTcgClicked = { navController.navigate(TCG_ROUTE) },
                onQrClicked = { navController.navigate(QR_ROUTE) },
            )
        }

        composable(ONBOARDING_ROUTE) {
            OnboardingNavigationGraph(
                startDestination = startDestination.split("/").getOrNull(1)?: AGE_SCREEN
            )
        }

        vgcNavGraph(navController = navController) { navController.navigate(TCG_ROUTE) }
        tcgNavGraph(navController = navController)
        qrNavGraph()
    }
}
