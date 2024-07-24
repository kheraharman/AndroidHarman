package com.android.data.network

import com.android.data.model.FruitsResponse
import com.android.data.model.Nutritions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface for accessing fruit-related data from the network.
 */
interface FruitsApi {

    /**
     * Retrieves a list of fruits with calories between the specified minimum and maximum.
     *
     * @return A [Response] containing a list of [FruitsResponse] objects.
     */
    @GET("calories?min=25&max=40")
    suspend fun getFruits(): Response<List<FruitsResponse>>

    /**
     * Retrieves nutritional information for a specific fruit identified by its ID.
     *
     * @param fruitId The unique identifier of the fruit.
     * @return A [Response] containing the [FruitsResponse] object for the specified fruit.
     */
    @GET("{fruitId}")
    suspend fun getNutritions(@Path("fruitId") fruitId: String): Response<FruitsResponse>
}