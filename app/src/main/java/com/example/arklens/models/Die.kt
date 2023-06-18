package com.example.arklens.models

import kotlin.random.Random

class Die(private val maximum: Int) {
    fun throwDie() : Int {
        return Random.Default.nextInt(1, maximum);
    }
}