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
    testOptions {
        unitTests.isReturnDefaultValues = true
        animationsDisabled = true
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.ui))
    implementation(project(Modules.network))
    kapt(Libraries.Hilt.hiltCompiler)
    implementation(Libraries.Hilt.hiltAndroid)
    implementation(Libraries.Hilt.viewModelCompose)
    implementation(Libraries.Hilt.navigationCompose)
    implementation(Libraries.appCompat)

    testImplementation(Testing.junit)
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockkAgentJvm)
    testImplementation(Testing.junit)
    testImplementation(Testing.kotlinxCoroutines)
}