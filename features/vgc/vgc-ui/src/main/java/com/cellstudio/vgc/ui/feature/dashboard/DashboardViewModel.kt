package com.cellstudio.vgc.ui.feature.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellstudio.vgc.ui.R
import com.cellstudio.vgc.ui.navigation.HOME_SCREEN
import com.cellstudio.vgc.ui.navigation.MOVE_SCREEN
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class DashboardViewModel @Inject constructor() : ViewModel() {
    private val _onNavigateToVgcDetails = MutableSharedFlow<Int>()
    val onNavigateToVgcDetails: SharedFlow<Int> = _onNavigateToVgcDetails.asSharedFlow()

    private val _onNavigateToMoveDetails = MutableSharedFlow<Int>()
    val onNavigateToMoveDetails: SharedFlow<Int> = _onNavigateToMoveDetails.asSharedFlow()

    private val _onNavigateToSub = MutableSharedFlow<String>()
    val onNavigateToSub: SharedFlow<String> = _onNavigateToSub.asSharedFlow()

    fun onNavigateToVgcDetails(id: Int) {
        viewModelScope.launch {
            _onNavigateToVgcDetails.emit(id)
        }
    }

    fun onNavigateToMoveDetails(id: Int) {
        viewModelScope.launch {
            _onNavigateToMoveDetails.emit(id)
        }
    }

    fun onBottomBarClicked(route: String) {
        viewModelScope.launch {
            _onNavigateToSub.emit(route)
        }
    }
}

internal sealed class BottomNavItem(
    val title: Int,
    val icon: ImageVector,
    val route: String
) {
    internal object Home :
        BottomNavItem(
            R.string.dashboard_vgc_home,
            Icons.Filled.Home,
            HOME_SCREEN
        )

    internal object Moves :
        BottomNavItem(
            R.string.dashboard_vgc_move,
            Icons.Filled.Face,
            MOVE_SCREEN
        )
}
