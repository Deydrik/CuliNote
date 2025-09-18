package com.example.culinote



import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {
    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(@Query("query") query: String,
                              @Query("apiKey") apiKey: String,
                              @Query("number") number: Int = 10,
                              @Query("addRecipeInformation") addInfo: Boolean = true,
                              @Query("instructionsRequired") instructions: Boolean = true,
                              @Query("addRecipeNutrition") nutrition: Boolean = false
                            ): Response<RecipeResponse>
}