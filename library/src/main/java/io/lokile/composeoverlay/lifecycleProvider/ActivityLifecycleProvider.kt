package io.lokile.composeoverlay.lifecycleProvider

import android.app.Activity
import kotlinx.coroutines.flow.MutableStateFlow

internal object ActivityLifecycleProvider {
    val activityLifecycle = MutableStateFlow<ActivityState?>(null)
}

internal sealed class ActivityState(val activity: Activity) {
    class Created(activity: Activity) : ActivityState(activity)
    class Started(activity: Activity) : ActivityState(activity)
    class Resumed(activity: Activity) : ActivityState(activity)
    class Paused(activity: Activity) : ActivityState(activity)
    class Stopped(activity: Activity) : ActivityState(activity)
}