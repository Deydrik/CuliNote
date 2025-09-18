package com.example.culinote

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FindRecipeActivity : AppCompatActivity(){
    private lateinit var apiService: RecipeApiService
    private lateinit var repository: RecipeRepo
    private lateinit var recipeDao: SavedRecipeDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_recipe)

        val database = RecipeDatabase.getDatabase(applicationContext)
        recipeDao = database.savedRecipeDao()

        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(RecipeApiService::class.java)
        repository = RecipeRepo(apiService)

        val searchButton = findViewById<Button>(R.id.searchButton)
        val queryInput = findViewById<EditText>(R.id.recipeQueryInput)
        val recipeRecyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        val backButton = findViewById<Button>(R.id.backButton)

        recipeRecyclerView.layoutManager = LinearLayoutManager(this)

        searchButton.setOnClickListener {
            val query = queryInput.text.toString()
            if (query.isNotBlank()) {
                lifecycleScope.launch {
                    try {
                        val response = repository.getRecipes(query, API_KEY)
                        if (response != null) {
                            recipeRecyclerView.adapter = RecipeAdapter(response.results)
                            { recipe -> lifecycleScope.launch {
                                val savedRecipe = SavedRecipe(
                                    recipe.id,
                                    recipe.title,
                                    recipe.summary,
                                    recipe.instructions,
                                    recipe.readyTime,
                                    recipe.dishTypes,
                                    recipe.ingredients,
                                    recipe.imageUrl,
                                    System.currentTimeMillis()
                                )
                                recipeDao.insert(savedRecipe)
                                Toast.makeText(
                                    this@FindRecipeActivity,
                                    "Recipe Saved!", Toast.LENGTH_SHORT
                                ).show()
                            }
                            }
                        }
                    }
                    catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this@FindRecipeActivity,
                            "Failed to get recipes", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        backButton.setOnClickListener {
            finish()
        }
    }
    companion object {
        private const val API_KEY = "27e06a834d494dcd882304cb8457b61a"
    }

}
