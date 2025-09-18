package com.example.culinote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter(private var recipes: List<Recipe>,
                    private val onSaveClick: (Recipe) -> Unit
): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.recipeTitle)
    val image: ImageView = view.findViewById(R.id.recipeImage)
    val saveButton: Button = view.findViewById(R.id.saveButton)
}
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.title.text = recipe.title
        Glide.with(holder.itemView).load(recipe.imageUrl).into(holder.image)
        holder.saveButton.setOnClickListener {onSaveClick(recipe)}
    }

    override fun getItemCount() = recipes.size

    fun updateRecipes(newRecipes: List<Recipe>) {
            this.recipes = newRecipes
            notifyDataSetChanged()

        }
}