plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = project.name
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "com.czyzewski.zendeskhometask.ZendeskTestRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = ProjectConfig.javaVersion
        targetCompatibility = ProjectConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packaging {
        resources {
            excludes += setOf(
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
                "**/attach_hotspot_windows.dll",
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
                "META-INF/licenses/ASM"
            )
        }
    }
}

dependencies {
    api(project(Modules.domain))
    implementation(project(Modules.ui))
    implementation(project(Modules.network))
    implementation(project(Modules.storage))
    implementation(project(Modules.ticketsList))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.10"))
    kapt(Libraries.hiltCompiler)
    implementation(Libraries.hiltAndroid)
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")
    val composeBom = platform("androidx.compose:compose-bom:2023.04.01")
    implementation(composeBom)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material3:material3-window-size-class")

    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    implementation("androidx.paging:paging-runtime:3.1.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha20")


    debugImplementation("androidx.compose.ui:ui-tooling")



    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.46.1")
    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.46.1")
    androidTestUtil("androidx.test:orchestrator:1.4.2")
    //androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:core-ktx:1.4.0")
    androidTestImplementation ("androidx.test:runner:1.4.0")
    androidTestImplementation ("androidx.test:rules:1.4.0")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation ("androidx.test:monitor:1.4.0")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.11")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.46.1")
}
