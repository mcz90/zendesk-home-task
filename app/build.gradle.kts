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


    testOptions {
        unitTests.isReturnDefaultValues = true
        animationsDisabled = true
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
    implementation(project(Modules.domain))
    implementation(project(Modules.ui))
    implementation(project(Modules.network))
    implementation(project(Modules.storage))
    implementation(project(Modules.ticketsList))


    kapt(Libraries.Hilt.hiltCompiler)
    implementation(Libraries.Hilt.hiltAndroid)
    implementation(platform(Libraries.kotlinBom))
    implementation(Libraries.Hilt.navigationCompose)
    implementation(Libraries.androidXCore)
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.lifecycleRuntimeKtx)
    implementation(Libraries.coreKtx)
    implementation("androidx.paging:paging-runtime:3.1.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha20")


    debugImplementation(Testing.Android.manifest)
    debugImplementation(Testing.Android.tooling)
    kaptAndroidTest(Testing.Android.hiltCompiler)
    androidTestUtil(Testing.Android.orchestrator)
    androidTestImplementation(Testing.Android.coreKtx)
    androidTestImplementation(Testing.Android.runner)
    androidTestImplementation(Testing.Android.rules)
    androidTestImplementation(Testing.Android.testMonitor)
    androidTestImplementation(Testing.Android.extJunit)
    androidTestImplementation(Testing.Android.composeUiTestJunit4)
    androidTestImplementation(Testing.Android.mockwebserver)
    androidTestImplementation(Testing.Android.hiltTesting)
}
