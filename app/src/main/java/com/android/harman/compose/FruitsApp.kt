package com.android.harman.compose

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.core_ui.NavigationItems
import com.android.features.view.HomeScreen
import com.android.features.view.NutritionScreen

/**
 * `FruitsApp` is the root composable function for the application's navigation structure.
 * It initializes the navigation controller and sets up the navigation graph for the app.
 *
 * The navigation graph defined in `FruitsNavHost` includes all the composable screens
 * that are part of the application. It starts with the home screen displaying a list of fruits
 * and allows navigation to detailed views like the nutrition information screen.
 *
 * @see FruitsNavHost for the navigation setup and the list of composables included.
 */
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