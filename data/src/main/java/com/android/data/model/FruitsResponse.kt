package com.android.data.model

import com.google.gson.annotations.SerializedName

/**
 * Represents the response for a fruit data fetch operation.
 *
 * @property name The name of the fruit.
 * @property id The unique identifier of the fruit.
 * @property family The family to which the fruit belongs.
 * @property order The order classification of the fruit.
 * @property genus The genus of the fruit.
 * @property nutritions The nutritional information of the fruit.
 */
data class FruitsResponse(
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("family") var family: String? = null,
    @SerializedName("order") var order: String? = null,
    @SerializedName("genus") var genus: String? = null,
    @SerializedName("nutritions") var nutritions: Nutritions? = Nutritions()
)