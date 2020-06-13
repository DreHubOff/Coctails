package com.studying.cocktails.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CocktailEntity(
    val serverId: String,
    val name: String,
    @PrimaryKey(autoGenerate = true)
    var _ID: Long? = null
)