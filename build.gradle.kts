plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.sqlDelight) apply false
    alias(libs.plugins.moko.multiplatform.resources) apply false
}

buildscript {
    dependencies {
        classpath(libs.sqldelight.gradle.plugin)
        classpath(libs.moko.resources.generator)
    }
}