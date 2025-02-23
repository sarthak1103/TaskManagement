package com.project.taskmanagement.firebase

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object AnalyticHelper {
    private val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    fun logEvent(eventName: String) {
        firebaseAnalytics.logEvent(eventName, null)
    }
    fun logTaskEvent(eventName: String, taskName: String) {
        val bundle = Bundle().apply {
            putString("task_name", taskName)
            putString("event_name", eventName)
        }
        firebaseAnalytics.logEvent(eventName, bundle)
    }
}