package com.example.arklens.repository
import com.example.arklens.models.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArklensApiService {
    private val defaultCount: Int
        get() = 23

    @GET("character/fake")
    fun getCharacters(@Query("count") count: Int = defaultCount): Call<List<Character>>
}