package com.example.culinote

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealTrackerActivity: AppCompatActivity() {
    private lateinit var database: RecipeDatabase
    private lateinit var recipeDao: SavedRecipeDao
    private lateinit var adapter: MealTrackerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_tracker)

        val recyclerView = findViewById<RecyclerView>(R.id.trackedMealsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MealTrackerActivity)
        adapter = MealTrackerAdapter(emptyList())
        recyclerView.adapter = adapter

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        database = Room.databaseBuilder(applicationContext,
            RecipeDatabase::class.java, "culinote-db")
            .fallbackToDestructiveMigrationFrom(2)
            .build()
        recipeDao = database.savedRecipeDao()

        lifecycleScope.launch{
            val savedRecipes = recipeDao.getAll()
            val sortedRecipes = savedRecipes.sortedBy{it.savedDate}
            withContext(Dispatchers.Main){
               adapter.updateMeals(sortedRecipes)
            }
        }
    }
}