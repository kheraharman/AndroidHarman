package com.android.data.model

import com.google.gson.annotations.SerializedName

/**
 * Represents the nutritional information of a fruit.
 *
 * @property calories The amount of calories in the fruit. Nullable to accommodate fruits where this data might not be available.
 * @property fat The amount of fat in grams. Nullable for cases where fat content is not specified.
 * @property sugar The amount of sugar in grams. Nullable to handle fruits without specified sugar content.
 * @property carbohydrates The amount of carbohydrates in grams. Nullable for fruits where this information is not available.
 * @property protein The amount of protein in grams. Nullable to accommodate fruits without specified protein content.
 */
data class Nutritions(

    @SerializedName("calories") var calories: Int? = null,
    @SerializedName("fat") var fat: Double? = null,
    @SerializedName("sugar") var sugar: Double? = null,
    @SerializedName("carbohydrates") var carbohydrates: Double? = null,
    @SerializedName("protein") var protein: Double? = null

)