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
    implementation(project(Modules.domain))
    implementation(project(Modules.ui))
    implementation(project(Modules.network))
    implementation(project(Modules.storage))
    kapt(Libraries.hiltCompiler)
    implementation(Libraries.hiltAndroid)

    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation(Libraries.viewModelCompose)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation(platform(Libraries.composeBom))
    implementation(Libraries.activityCompose)
    implementation(Libraries.activityComposeUi)
    implementation(Libraries.activityComposeUiGraphics)
    implementation(Libraries.activityComposeUiTooling)
    implementation(Libraries.composeMaterial3)
    androidTestImplementation(Libraries.composeUiTestJunit4)
    debugImplementation(Libraries.composeUiTooling)
    debugImplementation(Libraries.composeUiTestManifest)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.mockkAgentJvm)
    testImplementation(TestDependencies.kjunit)
    testImplementation(TestDependencies.kotlinxCoroutines)
    testImplementation(TestDependencies.kotlinxCoroutines)
    testImplementation("app.cash.turbine:turbine:0.13.0")
}