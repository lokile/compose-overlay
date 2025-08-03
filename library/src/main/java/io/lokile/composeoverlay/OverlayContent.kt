package io.lokile.composeoverlay

import androidx.compose.runtime.Composable

interface OverlayContent {

    /**
     * Renders the UI.
     *
     * @return true to keep the UI visible; or `false` to remove it from the activity.
     */
    @Composable
    fun render(): Boolean
}