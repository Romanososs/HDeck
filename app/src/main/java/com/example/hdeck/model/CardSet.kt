package com.example.hdeck.model

data class CardSet(
    val id: Int,
    val name: String,
    val slug: String,
    val type: SetType,
    val collectibleCount: Int,
    val collectibleRevealedCount: Int,
    val nonCollectibleCount: Int,
    val nonCollectibleRevealedCount: Int
) {
    override fun toString(): String {
        return name
    }
}