package com.studying.cocktails.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.studying.cocktails.db.model.CocktailEntity

@Database(entities = [CocktailEntity::class], version = 1, exportSchema = false)
abstract class CocktailsDataBase : RoomDatabase() {
    abstract fun getCocktailsDao(): CocktailsDao

    companion object {
        fun getInstance(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CocktailsDataBase::class.java,
            "cocktails"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}