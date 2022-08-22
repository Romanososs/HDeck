package com.example.hdeck.model.metadata

data class HeroClass(
    val id: Int,
    val slug: String,
    val name: String,
    val cardId: Int,
//    val heroPowerCardId: Int,
//    val alternateHeroCardIds: List<Int>,
) {
    override fun toString(): String {
        return name
    }
}

//{
//    "slug": "demonhunter",
//    "id": 14,
//    "name": "Demon Hunter",
//    "cardId": 56550,
//    "heroPowerCardId": 60224,
//    "alternateHeroCardIds": [
//    60238,
//    62491,
//    64697,
//    71077,
//    71078,
//    71079
//    ]
//}