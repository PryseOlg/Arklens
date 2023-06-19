package com.example.arklens.ui.die

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arklens.models.Die

class DieViewModel : ViewModel() {

    private val _diceValues = MutableLiveData<List<Die>>()
    val diceValues: LiveData<List<Die>> get() = _diceValues

    private val diceList = mutableListOf<Die>()

    init {
        // Инициализируем начальный список кубиков
        repeat(NUM_DICE) {
            diceList.add(Die(3))
            diceList.add(Die(4))
            diceList.add(Die(6))
            diceList.add(Die(8))
            diceList.add(Die(10))
            diceList.add(Die(12))
            diceList.add(Die(20))
            diceList.add(Die(100))
        }
        updateDiceValues()
    }

    fun rollDie(position: Int) {
        diceList[position].roll()
        updateDiceValues()
    }

    private fun updateDiceValues() {
        _diceValues.value = diceList.toList()
    }

    companion object {
        private const val NUM_DICE = 1
    }
}
