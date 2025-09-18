package com.example.culinote

import android.graphics.Color.RED
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MealTrackerAdapter(private var meals: List<SavedRecipe>):
    RecyclerView.Adapter<MealTrackerAdapter.ViewHolder>(){
        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val title: TextView = view.findViewById(R.id.mealTitle)
            val expiry: TextView = view.findViewById(R.id.mealExpiry)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_tracker_card, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = meals[position]
        holder.title.text = meal.title

        val expiryDateObject = Date(meal.savedDate)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        holder.expiry.text = "Expires on: ${dateFormat.format(expiryDateObject)}"

        val today = Calendar.getInstance().time

            val diff = expiryDateObject.time - today.time
            if(diff < 86400000) {
                holder.expiry.setTextColor(RED)
            }

    }
    override fun getItemCount(): Int {
        return meals.size
    }
    fun updateMeals(newMeals: List<SavedRecipe>){
        meals = newMeals
        notifyDataSetChanged()

    }
}