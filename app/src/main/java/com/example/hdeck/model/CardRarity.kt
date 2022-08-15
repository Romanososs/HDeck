package com.example.hdeck.model

data class CardRarity(
    val id: Int,
    val slug: String,
//    val craftingCost: List<Int>,
//    val dustValue: List<Int>,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
