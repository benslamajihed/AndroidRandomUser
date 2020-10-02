package com.jbs.ttechnique.utils

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority >= Log.INFO
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        // CrashAnaytics
        FirebaseCrashlytics.getInstance().log(message)
        if (t != null) {
            if (priority == Log.ERROR) {
                FirebaseCrashlytics.getInstance().recordException(t)
            }
        }
    }
}