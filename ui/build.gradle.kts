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
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
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