package com.studying.cocktails.network.model

import com.google.gson.annotations.SerializedName

data class DataHolderLarge(
    @SerializedName("drinks")
    val cocktails: List<CocktailLarge>
)