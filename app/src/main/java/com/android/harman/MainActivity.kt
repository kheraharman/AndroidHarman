package com.android.harman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.android.harman.compose.FruitsApp
import com.android.harman.ui.theme.AndroidHarmanTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity is the entry point of the application, extending [ComponentActivity].
 * It sets up the UI content within the `onCreate` method using Jetpack Compose.
 *
 * The UI is defined within a [Surface] container that fills the maximum available size.
 * It applies the application's theme, [AndroidHarmanTheme], and displays the [FruitsApp] composable
 * as its content.
 *
 * This activity is also marked with [AndroidEntryPoint] to integrate with Dagger-Hilt for dependency injection,
 * enabling the use of Hilt to inject dependencies into Android classes.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidHarmanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    FruitsApp()
                }
            }
        }
    }
}