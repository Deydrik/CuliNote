package com.example.culinote

import androidx.room.Room
import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [SavedRecipe::class],
    version = 3,
    exportSchema = false)
@TypeConverters
    (Converters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun savedRecipeDao(): SavedRecipeDao

    companion object{
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE saved_recipes ADD COLUMN summary TEXT NOT NULL DEFAULT ''")
                db.execSQL("ALTER TABLE saved_recipes ADD COLUMN dishTypes TEXT")
                db.execSQL("ALTER TABLE saved_recipes ADD COLUMN ingredients TEXT")
            }
        }

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "culinote-db"
                ).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                instance
            }
        }
    }
}