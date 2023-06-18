package com.example.arklens.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arklens.models.Character

class CharacterViewModel : ViewModel() {

    val liveData = MutableLiveData<Character>()

    fun init(character: Character) {

        liveData.postValue(character)
    }
}