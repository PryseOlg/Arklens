package com.example.arklens.interfaces

import com.example.arklens.models.Character

interface CharacterListener {
    fun onClick(character: Character)
}