package com.studying.cocktails.network.model

import com.google.gson.annotations.SerializedName

data class DataHolder(
    @SerializedName("drinks")
    val cocktails: List<CocktailSimple>
)