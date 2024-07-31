package com.android.data.repository

import com.android.common.model.FruitsResponse
import com.android.data.remote.FruitsApi
import com.android.domain.repository.FruitsRepository
import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Implementation of the [FruitsRepository] interface that handles data operations related to fruits.
 *
 * @property fruitApi The [FruitsApi] instance used for network requests.
 */
class FruitsRepositoryImpl @Inject constructor(private val fruitApi: FruitsApi) : FruitsRepository {

    override suspend fun getFruits(): Result<List<FruitsResponse>> {
        return try {
            val response = fruitApi.getFruits()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error fetching fruits: ${response.message()}"))
            }
        } catch (e: IllegalArgumentException) {
            Result.failure(Exception("Illegal Argument", e))
        } catch (e: UnknownHostException) {
            Result.failure(Exception("Unknown Host", e))
        } catch (e: IOException) {
            Result.failure(Exception("Network error", e))
        } catch (e: JsonSyntaxException) {
            Result.failure(Exception("JSON parsing error", e))
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
        } catch (e: IllegalArgumentException) {
            Result.failure(Exception("Illegal Argument", e))
        } catch (e: UnknownHostException) {
            Result.failure(Exception("Unknown Host", e))
        } catch (e: IOException) {
            Result.failure(Exception("Network error", e))
        } catch (e: JsonSyntaxException) {
            Result.failure(Exception("JSON parsing error", e))
        }
    }
}