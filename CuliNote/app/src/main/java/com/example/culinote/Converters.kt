package com.example.culinote

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
        // --- Converter for List<String> ---
        @TypeConverter
        fun fromStringList(stringList: List<String>?): String? {
            return stringList?.let { Gson().toJson(it) }
        }

        @TypeConverter
        fun toStringList(stringListJson: String?): List<String>? {
            return stringListJson?.let {
                val type = object : TypeToken<List<String>>() {}.type
                Gson().fromJson(it, type)
            }
        }

        // --- Converter for List<YourCustomObject> (e.g., List<Ingredient>) ---
        // Replace 'Ingredient' with your actual custom object type if needed
        // If you don't have a list of custom objects, you can omit this part.
        @TypeConverter
        fun fromIngredientList(ingredientList: List<Ingredient>?): String? {
            return ingredientList?.let { Gson().toJson(it) }
        }

        @TypeConverter
        fun toIngredientList(ingredientListJson: String?): List<Ingredient>? {
            return ingredientListJson?.let {
                // Make sure 'Ingredient' here matches your data class name
                val type = object : TypeToken<List<Ingredient>>() {}.type
                Gson().fromJson(it, type)
            }
        }

        // Add more converters if you have other types of lists, e.g., List<Int>
        // For List<Int>, List<Double>, etc., the approach is similar to List<String>
        @TypeConverter
        fun fromIntList(intList: List<Int>?): String? {
            return intList?.let { Gson().toJson(it) }
        }

        @TypeConverter
        fun toIntList(intListJson: String?): List<Int>? {
            return intListJson?.let {
                val type = object : TypeToken<List<Int>>() {}.type
                Gson().fromJson(it, type)
            }
        }
    }
