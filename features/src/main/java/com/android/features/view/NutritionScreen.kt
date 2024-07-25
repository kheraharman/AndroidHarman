package com.android.features.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.android.core_ui.ErrorMessage
import com.android.core_ui.LoadingScreen
import com.android.data.model.FruitsResponse
import com.android.data.network.Resource
import com.android.features.R
import com.android.features.viewmodel.NutritionViewModel

@Composable
fun NutritionScreen(
    backStackEntry: NavBackStackEntry,
    modifier: Modifier = Modifier,
    viewModel: NutritionViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val fruitId = backStackEntry.arguments?.getString("fruitId") ?: "1"

    LaunchedEffect(key1 = Unit) {
        viewModel.getNutrition(fruitId)
    }

    val nutrition by viewModel.state.collectAsStateWithLifecycle()

    var dataList by remember { mutableStateOf(FruitsResponse()) }

    when (val pageState = nutrition) {
        is Resource.Loading -> {
            LoadingScreen()
        }

        is Resource.Success -> {
            dataList = pageState?.data as FruitsResponse
        }

        is Resource.Error -> {
            ErrorMessage(message = pageState?.message, onDismiss = { onBackClick() })
        }

        else -> {}
    }

    dataList?.let {

        NutritionContent(it, onBackClick = {
            onBackClick()
        }, modifier)

    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
private fun FruitDetailsToolbar(
    fruit: FruitsResponse, onBackClick: () -> Unit
) {
    Surface {
        TopAppBar(modifier = Modifier
            .statusBarsPadding()
            .background(color = MaterialTheme.colorScheme.surface), title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = onBackClick, Modifier.align(Alignment.TopStart)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
                fruit.name?.let {
                    Text(
                        text = it, style = MaterialTheme.typography.titleLarge,
                        // As title in TopAppBar has extra inset on the left, need to do this: b/158829169
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                            .wrapContentSize(Alignment.Center)
                    )
                }

            }
        })
    }
}


@Composable
fun NutritionContent(
    fruit: FruitsResponse, onBackClick: () -> Unit, modifier: Modifier = Modifier
) {
    Column {

        FruitDetailsToolbar(fruit, onBackClick = { onBackClick() })
        Image(
            painter = painterResource(id = R.drawable.fruits_image),
            contentDescription = "Fruit Image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        NutritionDetailsContent(
            fruit,
            modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, start = 20.dp)
        )
    }
}

@Composable
fun NutritionDetailsContent(
    fruit: FruitsResponse, modifier: Modifier = Modifier
) {

    val nutrition = fruit.nutritions
    Row(modifier = modifier) {
        // First row
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(text = "Calories")
            Text(text = "Sugar")
            Text(text = "Fat")
            Text(text = "Carbohydrates")
            Text(text = "Protein")
        }
        // Second row
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(text = nutrition?.calories.toString())
            Text(text = nutrition?.sugar.toString())
            Text(text = nutrition?.fat.toString())
            Text(text = nutrition?.carbohydrates.toString())
            Text(text = nutrition?.protein.toString())
        }
    }
}