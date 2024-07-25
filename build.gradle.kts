/**
 * Top-level build file where you can add configuration options common to all sub-projects/modules.
 */
buildscript {
    /**
     * Configures the classpath dependencies for the build script itself.
     * Includes the Detekt plugin for static code analysis.
     */
    dependencies {
        classpath(Detekt.detekt)
    }
}

plugins {
    id(Plugin.androidApplication) version Plugin.Versions.androidPlugin apply false
    id(Plugin.kotlinAndroid) version Plugin.Versions.kotlinPlugin apply false
    id(Plugin.hiltAndroid) version Plugin.Versions.hiltPlugin apply false
    id(Plugin.androidLibrary) version Plugin.Versions.androidPlugin apply false
}