package com.example.arklens.adapters.utils
import java.net.URL

class CharacterUtils {
    fun getCharactersFromApi(count: Int = 10) {
        val json = URL("http://un1ver5e.keenetic.link:5000/character/fake?count=$count").readText()


    }
}