package com.example.arklens.ui.die

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arklens.models.Die

class DieViewModel : ViewModel() {

    private val _diceValue = MutableLiveData<Int>()
    val diceValue: LiveData<Int> get() = _diceValue

    private val die = Die(6)

    fun rollDie() {
        val result = die.throwDie()
        _diceValue.value = result
    }
}
