package com.android.data.repository

import com.android.data.model.FruitsResponse
import com.android.data.model.Nutritions
import com.android.data.network.FruitsApi
import com.google.gson.JsonSyntaxException
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever
import retrofit2.Response


class FruitsRepositoryTest {
    private lateinit var fruitsRepository: FruitsRepositoryImpl
    private val fruitsApi = Mockito.mock(FruitsApi::class.java)
    @Before
    fun setUp() {
        fruitsRepository = FruitsRepositoryImpl(fruitsApi)
    }

    @Test
    fun `getFruits returns success`() = runTest {
        val mockResponse = listOf(FruitsResponse("Apple", 1, "Nutrition"))
        whenever(fruitsApi.getFruits()).thenReturn(Response.success(mockResponse))

        val result = fruitsRepository.getFruits()

        assertTrue(result.isSuccess)
        assertEquals(mockResponse, result.getOrNull())
    }

    @Test
    fun `getFruits returns failure on network error`() = runTest {
        whenever(fruitsApi.getFruits()).thenThrow(JsonSyntaxException("Network error"))

        val result = fruitsRepository.getFruits()

        assertTrue(result.isFailure)
    }

    @Test
    fun `getFruits returns failure on error response`() = runTest {
        whenever(fruitsApi.getFruits()).thenReturn(Response.error(500, "".toResponseBody()))

        val result = fruitsRepository.getFruits()

        assertTrue(result.isFailure)
    }

    @Test
    fun `getNutrition returns success`() = runTest {
        val fruitId = 1
        val mockResponse = FruitsResponse(id = fruitId, name = "Apple", nutritions = Nutritions())
        whenever(fruitsApi.getNutritions(fruitId.toString())).thenReturn(Response.success(mockResponse))

        val result = fruitsRepository.getNutrition(fruitId.toString())

        assertTrue(result.isSuccess)
        assertEquals(mockResponse, result.getOrNull())
    }

    @Test
    fun `getNutrition returns failure on error response`() = runTest {
        val fruitId = "1"
        whenever(fruitsApi.getNutritions("1")).thenReturn(Response.error(500, "".toResponseBody()))

        val result = fruitsRepository.getNutrition(fruitId)

        assertTrue(result.isFailure)
    }

    @Test
    fun `getNutrition returns failure on network error`() = runTest {
        val fruitId = "1"
        whenever(fruitsApi.getNutritions("1")).thenThrow(JsonSyntaxException("Network error"))

        val result = fruitsRepository.getNutrition(fruitId)

        assertTrue(result.isFailure)
    }
}