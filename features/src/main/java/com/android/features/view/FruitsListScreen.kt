package com.android.features.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.core_ui.ErrorMessage
import com.android.core_ui.LoadingScreen
import com.android.data.model.FruitsResponse
import com.android.data.network.Resource
import com.android.features.R
import com.android.features.viewmodel.FruitsListViewModel

@Composable
fun FruitsListScreen(
    modifier: Modifier = Modifier,
    viewModel: FruitsListViewModel = hiltViewModel(),
    onFruitClick: (FruitsResponse) -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getFruitsNew()
    }

    val fruitsList by viewModel.state.collectAsStateWithLifecycle()

    var dataList by remember { mutableStateOf(listOf<FruitsResponse>()) }

    when (val pageState = fruitsList) {
        is Resource.Loading -> {
            LoadingScreen()
        }

        is Resource.Success -> {
            dataList = pageState?.data as List<FruitsResponse>
        }

        is Resource.Error -> {
            ErrorMessage(message = pageState?.message, onDismiss = { })
        }

        else -> {}
    }

    FruitsScreen(gardenPlants = dataList, modifier = modifier, onFruitClick = onFruitClick)

}


@Composable
private fun FruitsList(
    gardenPlants: List<FruitsResponse>,
    modifier: Modifier = Modifier,
    onFruitClick: (FruitsResponse) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier.imePadding(), contentPadding = PaddingValues(
            vertical = dimensionResource(id = com.android.core_ui.R.dimen.margin_extra_small)
        )
    ) {
        items(items = gardenPlants) { fruit ->
            FruitsListItem(fruits = fruit, onFruitClick = onFruitClick)
        }
    }
}


@Composable
fun FruitsScreen(
    gardenPlants: List<FruitsResponse>,
    modifier: Modifier = Modifier,
    onFruitClick: (FruitsResponse) -> Unit
) {
    if (gardenPlants.isEmpty()) {
        // Empty Screen Compose
    } else {
        FruitsList(gardenPlants = gardenPlants, modifier = modifier, onFruitClick = onFruitClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitsListItem(
    fruits: FruitsResponse, onFruitClick: (FruitsResponse) -> Unit
) {

    // Dimensions
    val cardSideMargin = dimensionResource(id = com.android.core_ui.R.dimen.card_side_margin)
    val marginNormal = dimensionResource(id = com.android.core_ui.R.dimen.margin_normal)
    val marginLarge = dimensionResource(id = com.android.core_ui.R.dimen.margin_large)
    val marginSmall = dimensionResource(id = com.android.core_ui.R.dimen.margin_small)
    val divider = dimensionResource(id = com.android.core_ui.R.dimen.divider)

    ElevatedCard(
        onClick = { onFruitClick(fruits) },
        modifier = Modifier.padding(
            start = cardSideMargin,
            end = cardSideMargin,
            bottom = dimensionResource(id = com.android.core_ui.R.dimen.card_bottom_margin)
        ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Modifier.padding(bottom = marginLarge)) {

            // Fruit name
            fruits.name?.let {
                Text(
                    text = it,
                    Modifier
                        .padding(vertical = marginNormal)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Divider(
                color = Color.White,
                thickness = divider
            )
            Column(
                Modifier
                    .padding(top = marginSmall, start = marginSmall)
            ) {
                Text(
                    text = stringResource(id = R.string.fruit_family),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = fruits.family ?: "",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Column(
                Modifier
                    .padding(top = marginSmall, start = marginSmall)
            ) {
                Text(
                    text = stringResource(id = R.string.fruit_genus),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = fruits.genus ?: "",
                    style = MaterialTheme.typography.labelSmall
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFruitsScreen() {
    val sampleFruits = listOf(
        FruitsResponse(name = "Apple", family = "Rosaceae", order = "Rosales", genus = "Malus"),
        FruitsResponse(name = "Banana", family = "Musaceae", order = "Zingiberales", genus = "Musa")
    )
    FruitsScreen(gardenPlants = sampleFruits, onFruitClick = {})
}