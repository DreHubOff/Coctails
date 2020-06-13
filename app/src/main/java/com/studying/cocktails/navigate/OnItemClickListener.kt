package com.studying.cocktails.navigate

import com.studying.cocktails.db.model.CocktailEntity

interface OnItemClickListener {
    fun onItemClick(cocktailId: String)
}