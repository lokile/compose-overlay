package io.lokile.composeoverlay.lifecycleProvider

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.startup.Initializer

internal class ActivityLifecycleInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        (context as? Application)?.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(
                activity: Activity,
                savedInstanceState: Bundle?
            ) {
                setState(ActivityState.Created(activity))
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (activity === ActivityLifecycleProvider.activityLifecycle.value?.activity) {
                    setState(null)
                }
            }

            override fun onActivityPaused(activity: Activity) {
                if (activity === ActivityLifecycleProvider.activityLifecycle.value?.activity) {
                    setState(ActivityState.Paused(activity))
                }
            }

            override fun onActivityResumed(activity: Activity) {
                setState(ActivityState.Resumed(activity))
            }

            override fun onActivitySaveInstanceState(
                activity: Activity,
                outState: Bundle
            ) {
            }

            override fun onActivityStarted(activity: Activity) {
                setState(ActivityState.Started(activity))
            }

            override fun onActivityStopped(activity: Activity) {
                if (activity === ActivityLifecycleProvider.activityLifecycle.value?.activity) {
                    setState(ActivityState.Stopped(activity))
                }
            }
        })
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        return emptyList()
    }

    private fun setState(state: ActivityState?) {
        ActivityLifecycleProvider.activityLifecycle.value = state
    }
}