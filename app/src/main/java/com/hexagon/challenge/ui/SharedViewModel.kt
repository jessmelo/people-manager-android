package com.hexagon.challenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val _pickedImageUri = MutableStateFlow<String?>(null)
    val pickedImageUri: StateFlow<String?> get() = _pickedImageUri

    fun updatePickedImageUri(uri: String?) {
        viewModelScope.launch {
            _pickedImageUri.emit(uri)
        }
    }
}