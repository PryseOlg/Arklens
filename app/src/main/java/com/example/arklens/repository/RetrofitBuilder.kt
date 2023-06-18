package com.example.arklens.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val url = "http://un1ver5e.keenetic.link:5000/"
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val arklensApi: ArklensApiService = getRetrofit().create(ArklensApiService::class.java)
}