plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")

}

android {
    namespace = "com.glyadgzl.random"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.glyadgzl.random"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
    implementation(libs.androidx.media3.exoplayer)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.coil.compose)

    //HILT


    // Hilt Core
    implementation ("com.google.dagger:hilt-android:2.50")
    ksp("com.google.dagger:hilt-android-compiler:2.50")
    // Hilt - ViewModel
    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    ksp ("androidx.hilt:hilt-compiler:1.1.0")

    // Hilt - Compose için
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    // ViewModel utilities for Compose
//    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:$compose_ui_version"


    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.0")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.7.2")
    //Navigation
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    implementation ("androidx.compose.runtime:runtime-livedata:1.0.0")

    implementation ("com.google.android.exoplayer:exoplayer-core:2.17.1")
    implementation ("com.google.android.exoplayer:exoplayer-ui:2.17.1")
}
