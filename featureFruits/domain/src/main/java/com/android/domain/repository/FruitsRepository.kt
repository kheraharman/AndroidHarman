package com.android.domain.repository

import com.android.common.model.FruitsResponse

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
