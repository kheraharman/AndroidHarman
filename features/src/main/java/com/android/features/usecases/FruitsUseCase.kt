package com.android.features.usecases

import com.android.data.model.FruitsResponse
import com.android.data.repository.FruitsRepository
import javax.inject.Inject

/**
 * Use case for managing fruit-related operations.
 *
 * This class encapsulates the logic for fetching a list of fruits and their nutritional information.
 * It acts as an intermediary between the repository layer and the UI, ensuring that data fetching
 * and transformation logic is kept away from the UI.
 *
 * @property fruitsRepository The repository responsible for fetching fruit data.
 */
class FruitsUseCase @Inject constructor(
    private val fruitsRepository: FruitsRepository
) {

    suspend fun getFruits(): Result<List<FruitsResponse>> {
        return fruitsRepository.getFruits()
    }

    suspend fun getNutrition(fruitId: String): Result<FruitsResponse> {
        return fruitsRepository.getNutrition(fruitId)
    }
}