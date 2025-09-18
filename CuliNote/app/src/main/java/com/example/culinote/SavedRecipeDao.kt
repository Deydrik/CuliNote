package com.example.culinote

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavedRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: SavedRecipe)

    @Query("SELECT * FROM saved_recipes")
    suspend fun getAll(): List<SavedRecipe>

    @Delete
    suspend fun delete(recipe: SavedRecipe)
}