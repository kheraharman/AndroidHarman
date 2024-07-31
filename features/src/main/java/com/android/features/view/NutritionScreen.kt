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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.android.core_ui.ErrorMessage
import com.android.core_ui.LoadingScreen
import com.android.data.model.FruitsResponse
import com.android.data.model.Nutritions
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
                            .padding(top = dimensionResource(id = com.android.core_ui.R.dimen.margin_extra_small))
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

        val marginLarge = dimensionResource(id = com.android.core_ui.R.dimen.margin_large)
        val marginSmall = dimensionResource(id = com.android.core_ui.R.dimen.margin_small)

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
                .padding(top = marginSmall, start = marginLarge, end = marginSmall)
        )
    }
}

@Composable
fun NutritionDetailsContent(
    fruit: FruitsResponse, modifier: Modifier = Modifier
) {

    val nutrition = fruit.nutritions
    Column(modifier = modifier) {
        NutritionItem("Calories", "${nutrition?.calories.toString()} cal")
        NutritionItem("Sugar", "${nutrition?.sugar.toString()} g")
        NutritionItem("Fat", "${nutrition?.fat.toString()} g")
        NutritionItem("Carbohydrates", "${nutrition?.carbohydrates.toString()} g")
        NutritionItem("Protein", "${nutrition?.protein.toString()} g")

    }

}

@Composable
fun NutritionItem(strHeading: String, strValue: String) {


    val marginSmall = dimensionResource(id = com.android.core_ui.R.dimen.margin_small)
    val divider = dimensionResource(id = com.android.core_ui.R.dimen.divider)

    Column {

        Row {
            Text(
                text = strHeading, modifier = Modifier
                    .weight(1f)
                    .padding(start = marginSmall)
            )
            Text(
                text = strValue, modifier = Modifier
                    .weight(1f)
                    .padding(start = marginSmall)
            )
        }
        Divider(color = Color.Black, thickness = divider)
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewNutritionContent() {
    val sampleFruit = FruitsResponse(
        name = "Apple", family = "Rosaceae", genus = "Malus", nutritions = Nutritions(
            calories = 52, sugar = 10.39, fat = 0.17, carbohydrates = 13.81, protein = 0.26
        )
    )
    NutritionContent(fruit = sampleFruit, onBackClick = {})
}