package com.android.data.repository

import com.android.data.model.FruitsResponse
import com.android.data.model.Nutritions
import com.android.data.network.FruitsApi
import com.google.gson.JsonSyntaxException
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response


class FruitsRepositoryTest {
    private lateinit var fruitsRepository: FruitsRepositoryImpl
    private val fruitsApi: FruitsApi = mockk()
    @Before
    fun setUp() {
        fruitsRepository = FruitsRepositoryImpl(fruitsApi)
    }

    @Test
    fun `getFruits returns success`() = runTest {
        val mockResponse = listOf(FruitsResponse("Apple", 1, "Nutrition"))
        coEvery { fruitsApi.getFruits() } returns Response.success(mockResponse)

        val result = fruitsRepository.getFruits()

        assertTrue(result.isSuccess)
        assertEquals(mockResponse, result.getOrNull())
    }

    @Test
    fun `getFruits returns failure on network error`() = runTest {
        coEvery { fruitsApi.getFruits() } throws JsonSyntaxException("Network error")

        val result = fruitsRepository.getFruits()

        assertTrue(result.isFailure)
    }

    @Test
    fun `getFruits returns failure on error response`() = runTest {
        coEvery { fruitsApi.getFruits() } returns Response.error(500, "".toResponseBody())

        val result = fruitsRepository.getFruits()

        assertTrue(result.isFailure)
    }

    @Test
    fun `getNutrition returns success`() = runTest {
        val fruitId = 1
        val mockResponse = FruitsResponse(id = fruitId, name = "Apple", nutritions = Nutritions())
        coEvery { fruitsApi.getNutritions(fruitId.toString()) } returns Response.success(mockResponse)

        val result = fruitsRepository.getNutrition(fruitId.toString())

        assertTrue(result.isSuccess)
        assertEquals(mockResponse, result.getOrNull())
    }

    @Test
    fun `getNutrition returns failure on error response`() = runTest {
        val fruitId = "1"
        coEvery { fruitsApi.getNutritions(fruitId) } returns Response.error(500, "".toResponseBody())

        val result = fruitsRepository.getNutrition(fruitId)

        assertTrue(result.isFailure)
    }

    @Test
    fun `getNutrition returns failure on network error`() = runTest {
        val fruitId = "1"
        coEvery { fruitsApi.getNutritions(fruitId) } throws JsonSyntaxException("Network error")

        val result = fruitsRepository.getNutrition(fruitId)

        assertTrue(result.isFailure)
    }
}