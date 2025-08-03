package io.lokile.composeoverlay

import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import io.lokile.composeoverlay.lifecycleProvider.ActivityLifecycleProvider
import io.lokile.composeoverlay.lifecycleProvider.ActivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn

object ComposeOverlay {
    private val event = MutableStateFlow<Event?>(null)

    init {
        val processOwner = ProcessLifecycleOwner.get()
        combine(
            event,
            ActivityLifecycleProvider.activityLifecycle
        ) { ui, activityEvent ->
            val activity = (activityEvent as? ActivityState.Resumed)
                ?.activity as? ComponentActivity
                ?: return@combine

            val composeUI = ui?.consume() ?: return@combine

            activity.addContentView(
                ComposeView(activity).also { view ->
                    view.setContent {
                        var visible by remember { mutableStateOf(true) }
                        if (!visible) return@setContent

                        visible = composeUI.render()
                        DisposableEffect(Unit) {
                            onDispose {
                                (view.parent as ViewGroup).removeView(view)
                            }
                        }
                    }
                },
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
        }
            .flowWithLifecycle(processOwner.lifecycle, Lifecycle.State.STARTED)
            .launchIn(processOwner.lifecycle.coroutineScope)
    }

    fun show(uiProvider: OverlayContent) {
        event.value = Event(uiProvider)
    }
}

private class Event(ui: OverlayContent) {
    private var ui: OverlayContent? = ui

    fun consume(): OverlayContent? {
        return ui.also { ui = null }
    }
}