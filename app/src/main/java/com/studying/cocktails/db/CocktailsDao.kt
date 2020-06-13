package com.studying.cocktails.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studying.cocktails.db.model.CocktailEntity
import io.reactivex.Flowable

@Dao
interface CocktailsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cocktails: List<CocktailEntity>)

    @Query("SELECT * FROM CocktailEntity WHERE serverId == :id")
    fun selectById(id: String): Flowable<List<CocktailEntity>>

    @Query("SELECT * FROM CocktailEntity")
    fun selectAll(): Flowable<List<CocktailEntity>>
}
