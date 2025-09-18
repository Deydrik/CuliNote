package com.example.culinote

class RecipeRepo(private val api: RecipeApiService) {
    suspend fun getRecipes(query: String, apiKey: String): RecipeResponse?{
        return api.searchRecipes(query, apiKey).body()
    }
}