package com.example.hdeck.model.metadata

import com.google.gson.annotations.SerializedName

data class CardType(
    val slug: String,
    val id: Int,
    val name: String,
    @SerializedName("gameModes")
    val gameModesIds: List<Int>
)

//{
//    "slug": "hero",
//    "id": 3,
//    "name": "Hero",
//    "gameModes": [
//    1,
//    4,
//    5
//    ]
//}
