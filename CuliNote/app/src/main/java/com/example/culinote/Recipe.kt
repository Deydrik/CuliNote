package com.example.culinote

import com.google.gson.annotations.SerializedName

data class Recipe(
    val id: Int,
    val title: String,
    @SerializedName("image") val imageUrl: String,
    val instructions: String,
    val summary: String,
    @SerializedName("readyInMinutes") val readyTime: Int,
    @SerializedName("dishTypes") val dishTypes: List<String>,
    @SerializedName("extendedIngredients") val ingredients: List<Ingredient>?
)
