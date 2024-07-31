package com.android.features.view

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.android.common.model.FruitsResponse
import com.android.presentation.ui.FruitsListItem
import org.junit.Rule
import org.junit.Test

class FruitsListScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun fruitsListItem_displaysCorrectFruitName() {
        // Prepare a mock fruit response
        val mockFruit = FruitsResponse(name = "Apple", family = "Rosaceae", genus = "Malus", order = "Rosales")

        // Set the composable to be tested
        composeTestRule.setContent {
            FruitsListItem(fruits = mockFruit, onFruitClick = {})
        }

        // Assert that the fruit name is displayed
        composeTestRule
            .onNodeWithText("Apple")
            .assertIsDisplayed()
    }
}