package com.example.arklens.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Карта Аркленса"
    }
    val text: LiveData<String> = _text

    fun init() {
    }
}