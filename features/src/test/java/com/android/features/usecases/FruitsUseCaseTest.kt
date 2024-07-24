package com.android.features.usecases

import com.android.data.model.FruitsResponse
import com.android.data.repository.FruitsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class FruitsUseCaseTest {

    private lateinit var fruitsUseCase: FruitsUseCase
    private val fruitsRepository: FruitsRepository = mock()

    @Before
    fun setUp() {
        fruitsUseCase = FruitsUseCase(fruitsRepository)
    }

    @Test
    fun `invoke - success`() = runBlocking {
        val expectedData = listOf(FruitsResponse("Apple"), FruitsResponse("Banana"))
        whenever(fruitsRepository.getFruits()).thenReturn(Result.success(expectedData))

        val result = fruitsUseCase.invoke()
        assertTrue(result.isSuccess)
        assertEquals(expectedData, result.getOrNull())
    }

    @Test
    fun `invoke - failure`() = runBlocking {
        whenever(fruitsRepository.getFruits()).thenReturn(Result.failure(RuntimeException("Error")))

        val result = fruitsUseCase.invoke()
        assertTrue(result.isFailure)
    }

    @Test
    fun `getNutrition - success`() = runBlocking {
        val fruitId = "apple"
        val expectedData = FruitsResponse("Apple", 1)
        whenever(fruitsRepository.getNutrition(fruitId)).thenReturn(Result.success(expectedData))

        val result = fruitsUseCase.getNutrition(fruitId)
        assertTrue(result.isSuccess)
        assertEquals(expectedData, result.getOrNull())
    }

    @Test
    fun `getNutrition - failure`() = runBlocking {
        val fruitId = "1"
        whenever(fruitsRepository.getNutrition(fruitId)).thenReturn(
            Result.failure(
                RuntimeException(
                    "Error"
                )
            )
        )

        val result = fruitsUseCase.getNutrition(fruitId)
        assertTrue(result.isFailure)
    }
}