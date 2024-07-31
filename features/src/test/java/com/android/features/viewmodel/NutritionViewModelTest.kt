package com.android.features.viewmodel

import com.android.data.model.FruitsResponse
import com.android.data.network.Resource
import com.android.features.usecases.FruitsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NutritionViewModelTest {

    private lateinit var viewModel: NutritionViewModel
    private val fruitsUseCase: FruitsUseCase = mockk()

    private val testDispatcher = TestCoroutineDispatcher()
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = NutritionViewModel(fruitsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the Main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getNutrition - success state`() = runTest {
        val fruitId = "1"
        val expectedData = FruitsResponse("Apple", 1)
        coEvery { fruitsUseCase.getNutrition(fruitId) } returns Result.success(expectedData)

        viewModel.getNutrition(fruitId)

        assertTrue(viewModel.state.first() is Resource.Success)
    }

    @Test
    fun `getNutrition - error state`() = runTest {
        val fruitId = "1"
        val errorMessage = "Error fetching data"
        coEvery { fruitsUseCase.getNutrition(fruitId) } returns Result.failure(RuntimeException(errorMessage))

        viewModel.getNutrition(fruitId)
        assertTrue(viewModel.state.first() is Resource.Error)
    }
}