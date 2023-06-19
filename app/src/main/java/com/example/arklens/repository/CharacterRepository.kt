package com.example.arklens.repository

import android.content.Context
import android.util.Log
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.arklens.models.Character
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.lang.Exception

class CharacterRepository(context: Context) {
    private val cache = CharacterCache(context)
    private val api = RetrofitBuilder.arklensApi
    /**
    * Safely fetches characters from cache or api, if no cache is found.
    * @return A list of characters or empty list if there was an error.
    */
    suspend fun getCharacters() : List<Character> {
        try {
            val cached = cache.getCachedCharacters()

            if (cached.isNotEmpty()) {
                return cached
            }
            return fetchCharacters()
        }
        catch (ex: Exception) {
            Log.e("ERROR", ex.message ?: "")
            return emptyList()
        }
    }

    /**
     * Fetches characters from remote api, caches the response and returns it
     */
    suspend fun fetchCharacters() : List<Character> {
        return try {
            val characters = api.getCharacters().await()
            cache.cacheCharacters(characters)
            characters
        } catch (ex: Exception) {
            emptyList()
        }
    }

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

    interface ArklensApiService {
        private val defaultCount: Int
            get() = 23

        @GET("character/fake")
        fun getCharacters(@retrofit2.http.Query("count") count: Int = defaultCount): Call<List<Character>>
    }

    class CharacterCache(private val context: Context) {

        /**
         * Gets cached characters or empty list if none are found
         */
        fun getCachedCharacters(): List<Character> {
            val db = CachedCharacterDatabase.getInstance(context)
            val dao = db.cachedCharacterDao()

            val cachedCharacters = dao.getAll()
            val gson = Gson()

            return cachedCharacters
                .map { gson.fromJson(it.json, Character::class.java) }
                .toList()
        }

        /**
         * Drops current cache and replaces it with provided value
         */
        fun cacheCharacters(characters: List<Character>) {
            val gson = Gson()
            val cachingCharacters = characters
                .map { CachedCharacter(it.id, gson.toJson(it)) }
                .toList()

            val db = CachedCharacterDatabase.getInstance(context)
            val dao = db.cachedCharacterDao()

            dao.dropAll()
            dao.saveAll(cachingCharacters)
        }

        @Entity(tableName = "cached_characters")
        data class CachedCharacter(
            @PrimaryKey val id: String,
            val json: String
        )

        @Dao
        interface CachedCharacterDao {
            @Query("SELECT * FROM cached_characters")
            fun getAll(): List<CachedCharacter>

            @Query("DELETE FROM cached_characters")
            fun dropAll()

            @Insert
            fun saveAll(characters: List<CachedCharacter>)
        }

        @Database(entities = [CachedCharacter::class], version = 1)
        abstract class CachedCharacterDatabase : RoomDatabase() {
            abstract fun cachedCharacterDao() : CachedCharacterDao

            companion object {
                @Volatile
                private var INSTANCE: CachedCharacterDatabase? = null

                fun getInstance(context: Context): CachedCharacterDatabase {
                    return INSTANCE ?: synchronized(this) {
                        val instance = Room.databaseBuilder(
                            context.applicationContext,
                            CachedCharacterDatabase::class.java,
                            "cached_characters_database"
                        ).build()
                        INSTANCE = instance
                        instance
                    }
                }
            }
        }

    }
}