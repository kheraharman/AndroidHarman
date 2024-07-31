package com.android.features.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.data.model.FruitsResponse
import com.android.data.network.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.android.features.usecases.FruitsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FruitsListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val fruitsUseCase: FruitsUseCase = mockk()
    private lateinit var viewModel: FruitsListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FruitsListViewModel(fruitsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the Main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getFruitsNew - success state`() = runTest {
        val expectedData = listOf(FruitsResponse("Apple"), FruitsResponse("Banana"))
        coEvery { fruitsUseCase.getFruits() } returns Result.success(expectedData)

        viewModel.getFruitsNew()
        assertTrue(viewModel.state.first() is Resource.Success)
    }

    @Test
    fun `getFruitsNew - error state`() = runTest {
        val errorMessage = "Error fetching data"
        coEvery { fruitsUseCase.getFruits() } returns Result.failure(RuntimeException(errorMessage))

        viewModel.getFruitsNew()
        assertTrue(viewModel.state.first() is Resource.Error)
    }
}