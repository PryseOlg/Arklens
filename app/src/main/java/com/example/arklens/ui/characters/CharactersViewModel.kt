package com.example.arklens.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arklens.models.Character
import com.example.arklens.repository.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import kotlin.math.log

class CharactersViewModel : ViewModel(){

    val liveData = MutableLiveData<List<Character>>()
    fun init(){
        CoroutineScope(Dispatchers.IO).launch {
            val result = RetrofitBuilder.arklensApi.getCharacters().await()
            liveData.postValue(result)
            Log.e("error:", result.joinToString("\n"))
        }

    }
}