package com.android.harman.compose.home

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
import com.android.features.view.FruitsListScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onFruitClick: (String) -> Unit = {}) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopAppBar()
        }
    ){ contentPadding ->
        FruitsListScreen(Modifier.padding(top = contentPadding.calculateTopPadding()), onFruitClick = {
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