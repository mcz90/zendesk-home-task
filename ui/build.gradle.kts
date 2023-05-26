plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
}

android {
    namespace = project.name
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = ProjectConfig.javaVersion
        targetCompatibility = ProjectConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }
}

dependencies {
    kapt(Libraries.hiltCompiler)
    implementation(Libraries.hiltAndroid)

    implementation(platform(Libraries.composeBom))
    implementation(Libraries.activityCompose)
    implementation(Libraries.activityComposeUi)
    implementation(Libraries.activityComposeUiGraphics)
    implementation(Libraries.activityComposeUiTooling)
    implementation(Libraries.composeMaterial3)
    androidTestImplementation(Libraries.composeUiTestJunit4)
    debugImplementation(Libraries.composeUiTooling)
    debugImplementation(Libraries.composeUiTestManifest)
}