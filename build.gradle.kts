// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath(Libraries.androidBuildTools)
        classpath(Libraries.kotlinGradlePlugin)
        classpath(Libraries.hiltAndroidGradlePlugin)

         // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}