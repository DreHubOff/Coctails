package com.studying.cocktails.network.model

import com.google.gson.annotations.SerializedName

data class CocktailSimple(
    @SerializedName("strDrink")
    val name: String,
    @SerializedName("idDrink")
    val cocktailId: String
)
