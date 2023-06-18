package com.example.arklens.models

data class Character(
    val personalInfo: PersonalInfo,
    val race: Race,
    val `class`: Class,
    val characteristicSet: CharacteristicSet,
    val statSet: StatSet,
    val skillSet: SkillSet,
    val inventory: Inventory) {
}