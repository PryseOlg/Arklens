package com.example.arklens.models

import kotlin.random.Random

class Die(private val maximum: Int) {
    var diceValue: Int = 1
        private set

    fun roll() {
        diceValue = Random.Default.nextInt(1, maximum + 1)
    }
}