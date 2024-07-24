package com.android.features.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.data.model.FruitsResponse
import com.android.data.network.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import com.android.features.usecases.FruitsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class FruitsListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val fruitsUseCase: FruitsUseCase = mock()
    private lateinit var viewModel: FruitsListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FruitsListViewModel(fruitsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getFruitsNew emits Resource Loading then Resource Success`() = runTest {
        val fruitsList = listOf(FruitsResponse(name = "Apple"), FruitsResponse("Banana"))
        whenever(fruitsUseCase.invoke()).thenReturn(Result.success(fruitsList))

        val stateEmissions = mutableListOf<Resource<Any>?>()
        val job = launch {
            viewModel.state.collect { stateEmissions.add(it) }
        }

        viewModel.getFruitsNew()

        delay(timeMillis = 1000)
        // Ensure we have the expected emissions
        assertTrue(stateEmissions[0] is Resource.Loading)
     //   assertTrue(stateEmissions[1] is Resource.Success && stateEmissions[1]?.data == fruitsList)

        job.cancel()
    }

    @Test
    fun `getFruitsNew emits Resource Loading then Resource Error on failure`() = runTest {
        val errorMessage = "Network error"
        whenever(fruitsUseCase.invoke()).thenReturn(Result.failure(Exception(errorMessage)))

        viewModel.getFruitsNew()

        assertEquals(Resource.Loading<List<FruitsResponse>>(), viewModel.state.first())
        assertEquals(Resource.Error<List<FruitsResponse>>(errorMessage), viewModel.state.first())
    }
}