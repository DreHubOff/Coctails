package com.studying.cocktails

import android.app.Application
import com.studying.cocktails.db.CocktailsDataBase

class App : Application() {

    lateinit var dataBase: CocktailsDataBase

    override fun onCreate() {
        super.onCreate()
        dataBase = CocktailsDataBase.getInstance(this)
    }
}