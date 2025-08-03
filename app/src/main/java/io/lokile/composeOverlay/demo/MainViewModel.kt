package io.lokile.composeOverlay.demo

import androidx.lifecycle.ViewModel
import io.lokile.composeoverlay.ComposeOverlay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _dialogResult = MutableStateFlow<Boolean?>(null)
    val dialogResult: StateFlow<Boolean?> = _dialogResult
    fun onShowDialogClick() {
        ComposeOverlay.show(
            HelloDialog(
                message = "Choose 'Yes' or 'No'",
                onSuccess = {
                    _dialogResult.value = true
                },
                onCancel = {
                    _dialogResult.value = false
                }
            )
        )
    }
}