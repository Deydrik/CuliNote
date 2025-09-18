package com.example.culinote

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val findRecipeButton = findViewById<Button>(R.id.findRecipeButton)
        if (findRecipeButton == null) {
            android.util.Log.e("MainActivity", "findRecipeButton is null!")
        } else {
            findRecipeButton.setOnClickListener {
                startActivity(Intent(this, FindRecipeActivity::class.java))
            }
        }

        val savedRecipeButton = findViewById<Button>(R.id.savedRecipeButton)
        if (savedRecipeButton == null) {
            android.util.Log.e("MainActivity", "savedRecipeButton is null!")
        } else {
            savedRecipeButton.setOnClickListener {
                startActivity(Intent(this, SavedRecipesActivity::class.java))
            }
        }

        val mealTrackerButton = findViewById<Button>(R.id.mealTrackerButton)
        if (mealTrackerButton == null) {
            android.util.Log.e("MainActivity", "mealTrackerButton is null!")
        } else {
            mealTrackerButton.setOnClickListener {
                startActivity(Intent(this, MealTrackerActivity::class.java))
            }
        }
    }
}