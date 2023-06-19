package com.example.arklens.ui.characters

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arklens.models.Character
import com.example.arklens.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel() : ViewModel(){

    val liveData = MutableLiveData<List<Character>>()
    fun init(context: Context){
        CoroutineScope(Dispatchers.IO).launch {
            val repo = CharacterRepository(context)
            val characters = repo.getCharacters()
            liveData.postValue(characters)
        }

    }
}