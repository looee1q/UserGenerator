package com.example.usergenerator.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_RANDOM_USER_URL: String = "https://randomuser.me/api/"

    private val converterFactory = GsonConverterFactory.create()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_RANDOM_USER_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    val api: RandomUserApi by lazy {
        retrofit.create(RandomUserApi::class.java)
    }
}