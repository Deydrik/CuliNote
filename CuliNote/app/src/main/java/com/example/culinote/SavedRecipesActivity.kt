package com.example.culinote

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch

class SavedRecipesActivity : AppCompatActivity() {

    private lateinit var recipeDao: SavedRecipeDao
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_recipes)

        val database = Room.databaseBuilder(
            applicationContext,
            RecipeDatabase::class.java,
            "culinote-db"
        ).fallbackToDestructiveMigrationFrom(2)
            .build()

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        recipeDao = database.savedRecipeDao()

        recyclerView = findViewById(R.id.savedRecipeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecipeAdapter(emptyList()) { recipe ->
            Toast.makeText(
                this@SavedRecipesActivity,
                "${recipe.title} selected",
                Toast.LENGTH_SHORT
            ).show()
        }
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            val savedRecipes: List<SavedRecipe> = recipeDao.getAll()

            val recipes: List<Recipe> = savedRecipes.map {
                Recipe(it.id,
                    it.title,
                    it.imageURL,
                    it.instructions.toString(),
                    it.summary,
                    it.readyTime,
                    it.dishTypes,
                    it.ingredients
                )
            }

            adapter.updateRecipes(recipes)

        }


    }
}

