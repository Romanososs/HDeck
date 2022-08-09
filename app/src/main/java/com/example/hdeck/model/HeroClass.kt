package com.example.hdeck.model

data class HeroClass(
    val id: Int,
    val slug: String,
    val name: String,
    val cardId: Int,
    val heroPowerCardId: Int,
    val alternateHeroCardIds: List<Int>,
) {
    override fun toString(): String {
        return name
    }
}
