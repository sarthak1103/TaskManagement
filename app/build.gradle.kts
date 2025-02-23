plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    alias(libs.plugins.hilt)
    alias(libs.plugins.firebase)
    alias(libs.plugins.crashlytics)
    id("com.google.firebase.firebase-perf")
}

android {
    namespace = "com.project.taskmanagement"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.project.taskmanagement"
        minSdk = 31
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Retrofit
    implementation(libs.retrofit)

    // OkHttp (for efficient network operations)
    implementation(libs.okhttp)

    // Gson Converter (for JSON parsing)
    implementation(libs.converter.gson)

    /*Hilt and navigation */
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    // implementation(libs.accompanist.navigation.animation)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    // firebase
    implementation(platform(libs.firebase.bom))
    implementation (libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics)
    implementation(libs.google.firebase.perf)
    //  Room Runtime
    implementation(libs.androidx.room.runtime)
    //  Room KTX
    implementation(libs.androidx.room.ktx)
    //  Room Compiler
    kapt(libs.androidx.room.compiler)
    implementation (libs.androidx.material.icons.extended)
    // mockk
    testImplementation(libs.mockk)
    androidTestImplementation (libs.mockk.android)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)

}
