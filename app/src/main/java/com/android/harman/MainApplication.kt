package com.android.harman

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * `MainApplication` serves as the base class for maintaining global application state.
 * It is annotated with [HiltAndroidApp], which triggers Hilt's code generation and
 * automatically creates a dependency injection container for the application.
 *
 * This container is attached to the application's lifecycle and provides dependencies
 * to it and to Android components within the application that are also annotated with Hilt annotations.
 */
@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}