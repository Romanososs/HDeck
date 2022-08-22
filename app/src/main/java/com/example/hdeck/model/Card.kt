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
) {
    constructor(
        card: CardApi,
        heroClass: String,
        type: String,
        set: String,
        rarity: String
    ) : this(
        card.id,
        card.slug,
        heroClass,
        type,
        set,
        rarity,
        card.artistName,
        card.image,
        card.name,
        card.flavorText
    )
}
