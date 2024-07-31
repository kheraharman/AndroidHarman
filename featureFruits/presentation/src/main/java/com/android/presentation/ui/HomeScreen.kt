package com.android.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * `HomeScreen` is the main screen of the application, showcasing a list of fruits.
 * It utilizes a [Scaffold] layout to provide a consistent material structure that includes
 * a top app bar and the content area for displaying the list of fruits.
 *
 * @param modifier A [Modifier] applied to the [Scaffold] to modify its layout or appearance.
 * @param onFruitClick A lambda function that is triggered when a fruit item is clicked. It takes
 * the ID of the clicked fruit as a parameter.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onFruitClick: (String) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopAppBar()
        }
    ) { contentPadding ->
        FruitsListScreen(
            Modifier.padding(top = contentPadding.calculateTopPadding()),
            onFruitClick = {
                onFruitClick(it.id.toString())
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Fruits",
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    )
}