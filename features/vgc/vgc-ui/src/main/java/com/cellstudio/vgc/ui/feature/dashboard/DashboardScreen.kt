package com.cellstudio.vgc.ui.feature.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cellstudio.vgc.ui.feature.home.navigation.HomeNavigation
import com.cellstudio.vgc.ui.feature.moves.list.MovesScreen
import com.cellstudio.vgc.ui.navigation.HOME_SCREEN
import com.cellstudio.vgc.ui.navigation.MOVE_SCREEN
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DashboardScreen(
    onNavigateToVgcDetails: (output: Int) -> Unit,
    onNavigateToMoveDetails: (output: Int) -> Unit,
) {
    val vm = hiltViewModel<DashboardViewModel>()
    val navController = rememberNavController()
    val currentBackStackState by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackState?.destination?.route

    LaunchedEffect(key1 = vm.onNavigateToMoveDetails) {
        vm.onNavigateToMoveDetails.collectLatest {
            onNavigateToMoveDetails(it)
        }
    }

    LaunchedEffect(key1 = vm.onNavigateToVgcDetails) {
        vm.onNavigateToVgcDetails.collectLatest {
            onNavigateToVgcDetails(it)
        }
    }

    LaunchedEffect(key1 = vm.onNavigateToSub) {
        vm.onNavigateToSub.collectLatest {
            navController.navigate(it) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                listOf(BottomNavItem.Home, BottomNavItem.Moves).forEachIndexed { _, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(stringResource(id = item.title)) },
                        selected = item.route == currentRoute,
                        onClick = { vm.onBottomBarClicked(item.route) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = HOME_SCREEN
        ) {
            composable(HOME_SCREEN) {
                HomeNavigation {
                    vm.onNavigateToVgcDetails(it)
                }
            }

            composable(MOVE_SCREEN) {
                MovesScreen(onNavigateToDetails = {
                    vm.onNavigateToMoveDetails(it)
                })
            }
        }
    }
}