package com.example.hdeck.model.metadata

import com.google.gson.annotations.SerializedName

data class MinionType(
    val slug: String,
    val id: Int,
    val name: String,
    @SerializedName("gameModes")
    val gameModesIds: List<Int>
)

//{
//    "slug": "bloodelf",
//    "id": 1,
//    "name": "Blood Elf",
//    "gameModes": [
//    7
//    ]
//}
