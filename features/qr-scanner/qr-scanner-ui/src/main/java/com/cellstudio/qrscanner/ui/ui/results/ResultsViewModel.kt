package com.cellstudio.qrscanner.ui.ui.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellstudio.core.clipboard.ClipboardService
import com.cellstudio.core.pm.PackageManager
import dagger.hilt.android.lifecycle.HiltViewModel
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
internal class ResultsViewModel @Inject constructor(
    private val packageManager: PackageManager,
    private val clipboardService: ClipboardService
) : ViewModel() {
    private val _resultState: MutableStateFlow<String> = MutableStateFlow("")
    val resultState: StateFlow<String> = _resultState.asStateFlow()

    private val _showToastMessage: MutableSharedFlow<String> = MutableSharedFlow()
    val showToastMessage: SharedFlow<String> = _showToastMessage.asSharedFlow()

    fun onOpenWebsite() {
        try {
            packageManager.openUniversalLink(resultState.value)
        } catch (exception: Exception) {
            viewModelScope.launch {
                _showToastMessage.emit(exception.message?: "Something went wrong!")
            }
        }
    }

    fun onCopyText() {
        viewModelScope.launch {
            val result = resultState.value
            clipboardService.putInto(result)
            _showToastMessage.emit("Copied: $result")
        }
    }

    fun onUpdateResult(result: String) {
        viewModelScope.launch {
            _resultState.update { result }
        }
    }
}