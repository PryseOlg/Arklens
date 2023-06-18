package com.example.arklens.ui.dice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiceViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Dice Fragment"
    }
    val text: LiveData<String> = _text
}