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

        buildConfigField(
            type = "String",
            name = "ZENDESK_TOKEN",
            value = "\"${property("zendesk.token")}\""
        )
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
    implementation(Libraries.Network.okHttp)
    implementation(Libraries.Network.okHttpLoggingInterceptor)
    implementation(Libraries.Network.networkingOkHttp)
    implementation(Libraries.Network.moshi)
    implementation(Libraries.Network.moshiKotlin)
    implementation(Libraries.Network.retrofit)
    implementation(Libraries.Network.moshiRetrofitConverter)
    implementation(Libraries.Hilt.hiltAndroid)
    kapt(Libraries.Hilt.hiltCompiler)
}