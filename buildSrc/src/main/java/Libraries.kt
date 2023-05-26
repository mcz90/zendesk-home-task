object Libraries {
    private const val androidBuildToolsVersion = "8.0.2"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10"

    private const val hiltAndroidGradlePluginVersion = "2.46.1"
    const val hiltAndroidGradlePlugin =
        "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"

    const val hiltVersion = "2.46.1"
    const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"


    private const val coreKtxVersion = "1.7.0"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

    private const val appCompatVersion = "1.4.0"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"


    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"


    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val moshiRetrofitConverter = "com.squareup.retrofit2:converter-moshi:2.9.0"


    const val okHttp = "com.squareup.okhttp3:okhttp:4.11.0"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.11.0"
    const val networkingOkHttp = "com.squareup.okhttp3:okhttp:4.11.0"
    const val networkingOkHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.11.0"
    const val moshi = "com.squareup.moshi:moshi:1.13.0"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.13.0"


    const val composeBom = "androidx.compose:compose-bom:2022.10.00"
    const val activityCompose = "androidx.activity:activity-compose:1.7.2"
    const val activityComposeUi = "androidx.compose.ui:ui"
    const val activityComposeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val activityComposeUiTooling = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
}