// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.firebase) apply false
    alias(libs.plugins.crashlytics) apply false
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
}