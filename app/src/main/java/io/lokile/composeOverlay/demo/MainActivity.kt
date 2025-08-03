package io.lokile.composeOverlay.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .safeDrawingPadding()
                    .padding(16.dp)
            ) {
                Button(
                    modifier = Modifier.safeDrawingPadding(),
                    onClick = { viewModel.onShowDialogClick() }
                ) { Text(text = "Show dialog") }

                val result by viewModel.dialogResult.collectAsStateWithLifecycle()
                if (result != null) {
                    Text(
                        "You choose: ${
                            if (result == true) {
                                "Yes"
                            } else {
                                "No"
                            }
                        }"
                    )
                }
            }
        }
    }
}