package com.android.harman.compose

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.core_ui.NavigationItems
import com.android.features.view.NutritionScreen
import com.android.harman.compose.home.HomeScreen

@Composable
fun FruitsApp() {
    val navController = rememberNavController()
    FruitsNavHost(navController = navController)
}

@Composable
fun FruitsNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavigationItems.HomeFruits.route) {
        composable(route = NavigationItems.HomeFruits.route) {
            HomeScreen(
                onFruitClick = {
                    navController.navigate(NavigationItems.Calories.createRoute(it))
                }
            )
        }

        composable(
            route = NavigationItems.Calories.route,
            arguments = NavigationItems.Calories.navArguments
        ) { backStackEntry ->
            NutritionScreen(backStackEntry,  onBackClick = {
                navController.navigateUp()
            })
        }
    }
}