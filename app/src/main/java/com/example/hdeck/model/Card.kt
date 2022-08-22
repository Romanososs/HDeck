package com.example.hdeck.model

data class Card(
    val id: Int,
    val slug: String,
    val heroClass: String,
    val type: String,
    val set: String,
    val rarity: String,
    val artistName: String,
    val image: String,
    val name: String,
    val flavorText: String,
//    val childIds: List<Int>,
)
