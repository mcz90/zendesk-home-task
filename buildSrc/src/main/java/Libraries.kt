object Libraries {
    const val androidBuildTools = "com.android.tools.build:gradle:8.0.2"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10"
    const val coreKtx = "androidx.core:core-ktx:1.7.0"
    const val kotlinBom = "org.jetbrains.kotlin:kotlin-bom:1.8.10"
    const val appCompat = "androidx.appcompat:appcompat:1.6.1"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
    const val androidXCore = "androidx.appcompat:appcompat:1.6.1"

    object Hilt {

        private const val hiltAndroidGradlePluginVersion = "2.46.1"
        const val hiltAndroidGradlePlugin =
            "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"
        const val hiltVersion = "2.46.1"
        const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"
        const val navigationCompose ="androidx.hilt:hilt-navigation-compose:1.0.0"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val moshiRetrofitConverter = "com.squareup.retrofit2:converter-moshi:2.9.0"
        const val okHttp = "com.squareup.okhttp3:okhttp:4.11.0"
        const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.11.0"
        const val networkingOkHttp = "com.squareup.okhttp3:okhttp:4.11.0"
        const val moshi = "com.squareup.moshi:moshi:1.13.0"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.13.0"
    }

    object Compose {
        const val activityCompose = "androidx.activity:activity-compose:1.7.2"
        const val composeBom = "androidx.compose:compose-bom:2023.04.01"
        const val composeMaterial3 = "androidx.compose.material3:material3"
        const val composeFoundation = "androidx.compose.foundation:foundation"
        const val composeUi = "androidx.compose.ui:ui"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    }
}