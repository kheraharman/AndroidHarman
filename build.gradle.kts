// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugin.androidApplication) version Plugin.Versions.androidPlugin apply false
    id(Plugin.kotlinAndroid) version Plugin.Versions.kotlinPlugin apply false
    id(Plugin.hiltAndroid) version Plugin.Versions.hiltPlugin apply false
    id(Plugin.androidLibrary) version Plugin.Versions.androidPlugin apply false
}