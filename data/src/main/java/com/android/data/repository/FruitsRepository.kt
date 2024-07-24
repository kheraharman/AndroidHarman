package com.android.data.repository

import com.android.data.model.FruitsResponse
import com.android.data.network.FruitsApi
import javax.inject.Inject

/**
 * Defines the contract for accessing fruit data. This includes fetching a list of fruits and
 * obtaining nutritional information for a specific fruit.
 */
interface FruitsRepository {
    /**
     * Fetches a list of fruits with their basic information.
     *
     * @return A [Result] containing a list of [FruitsResponse] objects on success or an exception on failure.
     */
    suspend fun getFruits(): Result<List<FruitsResponse>>

    /**
     * Fetches the nutritional information for a specific fruit identified by its ID.
     *
     * @param fruitId The unique identifier of the fruit.
     * @return A [Result] containing the [FruitsResponse] object for the specified fruit on success or an exception on failure.
     */
    suspend fun getNutrition(fruitId: String): Result<FruitsResponse>
}

class FruitsRepositoryImpl @Inject constructor(private val fruitApi: FruitsApi) : FruitsRepository {

    override suspend fun getFruits(): Result<List<FruitsResponse>> {
        return try {
            val response = fruitApi.getFruits()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error fetching fruits: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Network error", e))
        }
    }

    override suspend fun getNutrition(fruitId: String): Result<FruitsResponse> {
        return try {
            val response = fruitApi.getNutritions(fruitId)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error fetching Nutritions: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Network error", e))
        }
    }
}