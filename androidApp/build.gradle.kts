plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}
group = "com.rob.lights"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.2")
    implementation("androidx.core:core-ktx:1.3.2")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.activity:activity-ktx:1.2.0-beta01")
    implementation("androidx.fragment:fragment-ktx:1.3.0-beta01")

    implementation("androidx.compose.ui:ui:1.0.0-alpha05")
    // Tooling support (Previews, etc.)
    implementation("androidx.ui:ui-tooling:1.0.0-alpha05")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.0-alpha05")
    // Material Design
    implementation("androidx.compose.material:material:1.0.0-alpha05")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.0.0-alpha05")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-alpha05")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-alpha05")

    implementation("io.coil-kt:coil:1.0.0")
    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.3.1")
}
android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.rob.lights.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.0"
        kotlinCompilerExtensionVersion = "1.0.0-alpha05"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
