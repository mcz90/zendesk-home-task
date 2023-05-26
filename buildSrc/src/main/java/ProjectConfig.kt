import org.gradle.api.JavaVersion

object ProjectConfig {
    const val namespace = "com.czyzewski.zendeskhometask"
    const val appId = "com.czyzewski.zendeskhometask"
    const val compileSdk = 33
    const val minSdk = 24
    const val versionCode = 1
    const val versionName = "1.0"
    val javaVersion = JavaVersion.VERSION_17
    val jvmTarget = "17"
}