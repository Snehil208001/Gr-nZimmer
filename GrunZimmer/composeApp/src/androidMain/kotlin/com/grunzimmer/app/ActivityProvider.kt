package com.grunzimmer.app

import android.app.Activity
import java.lang.ref.WeakReference

object ActivityProvider {
    private var currentActivity: WeakReference<Activity?> = WeakReference(null)

    fun setActivity(activity: Activity) {
        currentActivity = WeakReference(activity)
    }

    fun getActivity(): Activity? {
        return currentActivity.get()
    }
}