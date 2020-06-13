package com.studying.cocktails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studying.cocktails.db.model.CocktailEntity
import com.studying.cocktails.navigate.OnItemClickListener
import kotlinx.android.synthetic.main.item.view.*

class ListAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ListAdapter.CocktailHolder>() {
    private val cocktailList = mutableListOf<CocktailEntity>()

    fun update(list: List<CocktailEntity>) {
        cocktailList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailHolder =
        CocktailHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    override fun getItemCount() = cocktailList.size

    override fun onBindViewHolder(holder: CocktailHolder, position: Int) {
        holder.bind(position, cocktailList[position].name)
        holder.itemView.setOnClickListener { listener.onItemClick(cocktailList[position].serverId) }
    }

    class CocktailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, name: String) = itemView.apply {
            cocktail_id_view.text = position.toString()
            cocktail_name_view.text = name
        }
    }
}