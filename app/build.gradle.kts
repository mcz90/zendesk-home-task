plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = ProjectConfig.namespace
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
                "META-INF/licenses/ASM"
            )
        }
    }
}

dependencies {
    api(project(":ui"))
    implementation(project(":domain"))
    implementation(project(":network"))
    implementation(project(":storage"))

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.10"))


    kapt(Libraries.hiltCompiler)
    implementation(Libraries.hiltAndroid)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")



    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
