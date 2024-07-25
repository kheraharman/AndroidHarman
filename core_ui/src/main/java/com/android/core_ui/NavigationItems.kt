package com.android.core_ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Represents the navigation destinations within the app.
 *
 * This sealed class is used to define all possible navigation routes in the application, along with any arguments
 * they might require. Each navigation item is represented as a distinct object within this sealed class, allowing
 * for type-safe navigation and argument passing.
 *
 * @property route The navigation route as a string. This is used by the navigation component to identify the destination.
 * @property navArguments A list of arguments that the navigation destination might require. These are defined using
 * [NamedNavArgument] which allows for type-safe argument passing.
 */
sealed class NavigationItems(
    val route: String, val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object HomeFruits : NavigationItems("home")

    data object Calories : NavigationItems(
        route = "fruitsCalories/{fruitId}", navArguments = listOf(navArgument("fruitId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(fruitId: String) = "fruitsCalories/${fruitId}"
    }

}