package com.android.features.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
    // Call reportFullyDrawn when the garden list has been rendered
    //val gridState = rememberLazyGridState()
    //ReportDrawnWhen { gridState.layoutInfo.totalItemsCount > 0 }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier.imePadding(), contentPadding = PaddingValues(
            horizontal = 10.dp, vertical = 10.dp
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
        //EmptyGarden(onAddPlantClick, modifier)
    } else {
        FruitsList(gardenPlants = gardenPlants, modifier = modifier, onFruitClick = onFruitClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FruitsListItem(
    fruits: FruitsResponse, onFruitClick: (FruitsResponse) -> Unit
) {

    // Dimensions
    val cardSideMargin = dimensionResource(id = R.dimen.card_side_margin)
    val marginNormal = dimensionResource(id = R.dimen.margin_normal)

    ElevatedCard(
        onClick = { onFruitClick(fruits) },
        modifier = Modifier.padding(
            start = cardSideMargin,
            end = cardSideMargin,
            bottom = dimensionResource(id = R.dimen.card_bottom_margin)
        ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(Modifier.fillMaxWidth()) {

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

            Text(
                text = stringResource(id = R.string.fruit_family),
                Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = fruits.family ?: "",
                Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelSmall
            )

            Text(
                text = stringResource(id = R.string.fruit_order),
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = marginNormal),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = fruits.order ?: "",
                Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = stringResource(id = R.string.fruit_genus),
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = marginNormal),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = fruits.genus ?: "",
                Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}