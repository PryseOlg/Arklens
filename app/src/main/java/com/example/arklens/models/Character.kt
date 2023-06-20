package com.example.arklens.models


data class Character(
    val id: String,
    val personalInfo: PersonalInfo,
    val race: Race,
    val `class`: Class,
    val characteristics: CharacteristicSet,
    val stats: StatSet,
    val skills: SkillSet,
    val inventory: Inventory) {
}