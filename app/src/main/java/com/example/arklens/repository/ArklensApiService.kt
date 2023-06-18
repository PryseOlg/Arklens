package com.example.arklens.repository
import com.example.arklens.models.Character
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArklensApiService {
    @GET("character/fake")
    fun getCharacters(@Query("count") count: Int = 5): Call<List<Character>>
}