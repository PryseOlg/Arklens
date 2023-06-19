package com.example.arklens.ui.full_character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arklens.models.Character

class FullCharacterViewModel : ViewModel() {

    val liveData = MutableLiveData<Character>()

    fun init(character: Character) {

        liveData.postValue(character)
    }
}