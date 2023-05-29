// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath(Libraries.androidBuildTools)
        classpath(Libraries.kotlinGradlePlugin)
        classpath(Libraries.Hilt.hiltAndroidGradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = ProjectConfig.jvmTarget
            freeCompilerArgs = freeCompilerArgs + "-include-runtime"
        }
    }


}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}