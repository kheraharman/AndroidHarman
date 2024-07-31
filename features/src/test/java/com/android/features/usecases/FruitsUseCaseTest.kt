package com.android.features.usecases

import com.android.data.model.FruitsResponse
import com.android.data.repository.FruitsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FruitsUseCaseTest {

    private lateinit var fruitsUseCase: FruitsUseCase
    private val fruitsRepository: FruitsRepository = mockk()

    @Before
    fun setUp() {
        fruitsUseCase = FruitsUseCase(fruitsRepository)
    }

    @Test
    fun `getFruits - success`() = runBlocking {
        val expectedData = listOf(FruitsResponse("Apple"), FruitsResponse("Banana"))
        coEvery { fruitsRepository.getFruits() } returns Result.success(expectedData)

        val result = fruitsUseCase.getFruits()
        assertTrue(result.isSuccess)
        assertEquals(expectedData, result.getOrNull())
    }

    @Test
    fun `getFruits - failure`() = runBlocking {
        coEvery { fruitsRepository.getFruits() } returns Result.failure(RuntimeException("Error"))

        val result = fruitsUseCase.getFruits()
        assertTrue(result.isFailure)
    }

    @Test
    fun `getNutrition - success`() = runBlocking {
        val fruitId = "apple"
        val expectedData = FruitsResponse("Apple", 1)
        coEvery { fruitsRepository.getNutrition(fruitId) } returns Result.success(expectedData)

        val result = fruitsUseCase.getNutrition(fruitId)
        assertTrue(result.isSuccess)
        assertEquals(expectedData, result.getOrNull())
    }

    @Test
    fun `getNutrition - failure`() = runBlocking {
        val fruitId = "1"
        coEvery { fruitsRepository.getNutrition(fruitId) } returns Result.failure(RuntimeException("Error"))

        val result = fruitsUseCase.getNutrition(fruitId)
        assertTrue(result.isFailure)
    }
}