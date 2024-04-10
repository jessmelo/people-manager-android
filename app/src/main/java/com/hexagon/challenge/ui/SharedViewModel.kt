package com.hexagon.challenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SnackbarMessage(
    val show: Boolean,
    val message: String
)

class SharedViewModel : ViewModel() {
    private val _pickedImageUri = MutableStateFlow<String?>(null)
    val pickedImageUri: StateFlow<String?> get() = _pickedImageUri
    private val _snackbarMessage = MutableStateFlow(SnackbarMessage(show = false, message = ""))
    val snackbarMessage = _snackbarMessage.asStateFlow()

    fun updatePickedImageUri(uri: String?) {
        viewModelScope.launch {
            _pickedImageUri.emit(uri)
        }
    }

    fun showSnackBar(show: Boolean, message: String) {
        viewModelScope.launch {
            _snackbarMessage.emit(SnackbarMessage(show = show, message = message))
        }
    }
}