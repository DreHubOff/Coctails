package com.studying.cocktails.network

import com.studying.cocktails.network.model.CocktailLarge
import com.studying.cocktails.network.model.DataHolder
import com.studying.cocktails.network.model.DataHolderLarge
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object ApiService {
    private const val END_POINT = "https://www.thecocktaildb.com/api/json/v1/1/"
    private val cocktailApi: CocktailApi

    fun simpleCocktailsList() = cocktailApi.simpleData("Alcoholic")
    fun findCocktailById(id: String) = cocktailApi.largeData(id)

    interface CocktailApi {
        @GET("filter.php")
        fun simpleData(@Query("a") type: String): Single<DataHolder>

        @GET("lookup.php")
        fun largeData(@Query("i") id: String): Single<DataHolderLarge>
    }


    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(END_POINT)
            .client(client)
            .build()
        cocktailApi = retrofit.create(CocktailApi::class.java)
    }
}