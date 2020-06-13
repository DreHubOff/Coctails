package com.studying.cocktails.network.model

import com.google.gson.annotations.SerializedName

data class CocktailLarge(
@SerializedName("strDrink")
val name: String,
@SerializedName("strCategory")
val category: String,
@SerializedName("strDrinkThumb")
val imageUrl: String,
@SerializedName("strInstructions")
val instructions: String,
@SerializedName("strIngredient1")
val ingredient1: String,
@SerializedName("strIngredient2")
val ingredient2: String,
@SerializedName("strIngredient3")
val ingredient3: String,
@SerializedName("strIngredient4")
val ingredient4: String

)