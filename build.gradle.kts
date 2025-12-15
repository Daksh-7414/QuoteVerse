// Project-level build.gradle.kts

plugins {
    id("com.android.application") version "8.3.1" apply false
    id("com.android.library") version "8.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.22" apply false
}

// Optional: You can keep this if you have old-style classpath dependencies
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")
    }
}

// Remove allprojects { repositories { ... } } because
// dependencyResolutionManagement in settings.gradle.kts already handles repositories
