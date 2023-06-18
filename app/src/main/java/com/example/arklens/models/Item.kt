package com.example.arklens.models

data class Item(
    val name: String,
    val price: Money,
    val imageUrl: String,
    val quantity: Int = 1) {
}