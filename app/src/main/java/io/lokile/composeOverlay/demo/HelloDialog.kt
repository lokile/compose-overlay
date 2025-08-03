package io.lokile.composeOverlay.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.lokile.composeoverlay.OverlayContent

class HelloDialog(
    private val message: String,
    private val onSuccess: () -> Unit,
    private val onCancel: () -> Unit
) : OverlayContent {
    @Composable
    override fun render(): Boolean {
        var isVisible by remember { mutableStateOf(true) }

        Dialog(onDismissRequest = { isVisible = false }) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .safeDrawingPadding()
            ) {
                Text(text = message)

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {
                        onSuccess()
                        isVisible = false
                    }) {
                        Text("Yes")
                    }
                    Button(onClick = {
                        onCancel()
                        isVisible = false
                    }) {
                        Text("No")
                    }
                }
            }
        }

        return isVisible
    }
}