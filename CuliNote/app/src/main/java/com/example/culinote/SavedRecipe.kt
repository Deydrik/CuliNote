package com.example.culinote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_recipes")
data class SavedRecipe (
   @PrimaryKey val id: Int,
           val title: String,
           val summary: String,
           val instructions: String?,
           val readyTime: Int,
           val dishTypes: List<String>,
           val ingredients: List<Ingredient>?,
           val imageURL: String,
           val savedDate: Long
)
