package com.example.recipeappfirebase_optional

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class myAdapter(private val recipes:ArrayList<ArrayList<String>>): RecyclerView.Adapter<myAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var recipe=recipes[position]
        var recipetv=""
        for (i in recipe.indices){
           recipetv+=recipe[i]+"\n"
        }
        holder.itemView.apply {
            tvrecipe.text= recipetv
        }
    }

    override fun getItemCount()=recipes.size
}