package com.cellstudio.qrscanner.ui.ui.home

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellstudio.core.pm.PackageManager
import com.cellstudio.qrscanner.ui.analyzer.BarcodeScanService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CameraViewModel @Inject constructor(
    private val barcodeScanService: BarcodeScanService,
    private val packageManager: PackageManager
) : ViewModel() {
    private val _cameraState = MutableStateFlow<Boolean>(false)
    val cameraState:StateFlow<Boolean> = _cameraState.asStateFlow()

    private val _onResult = MutableSharedFlow<String>()
    val onResult: SharedFlow<String> = _onResult.asSharedFlow()

    private val _onSettingsClicked = MutableSharedFlow<Unit>()
    val onSettingsClicked: SharedFlow<Unit> = _onSettingsClicked.asSharedFlow()

    private var resultJob: Job?= null

    fun onAccessUpdated(access: Boolean) {
        viewModelScope.launch {
            _cameraState.update { access }
        }
    }

    fun onResult(result: String) {
        if (resultJob?.isActive == false || resultJob == null) {
            resultJob = viewModelScope.launch {
                _onResult.emit(result)
                // Delay to debounce
                delay(1000L)
            }
        }
    }

    fun onImageUpload(uri: Uri) {
        barcodeScanService.scanImage(
            uri,
            onError = {
                it.printStackTrace()
            },
            onSuccess = {
                onResult(it)
            }
        )
    }

    fun onSettingsClicked() {
        viewModelScope.launch {
            _onSettingsClicked.emit(Unit)
        }
    }

    fun onProvideAccessClicked() {
        packageManager.openApplicationDetailsSettings()
    }
}